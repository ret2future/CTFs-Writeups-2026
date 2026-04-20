import base64
import io
import logging
import math
import os
from contextlib import asynccontextmanager
from pathlib import Path
from typing import Annotated, Dict, List, Optional

import numpy as np
from fastapi import FastAPI, HTTPException, Query
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import FileResponse, JSONResponse
from fastapi.staticfiles import StaticFiles
from PIL import Image
from pydantic import BaseModel, ConfigDict, Field, field_validator

from models.inference import (
    TOM_SCORE_THRESHOLD,
    CelebrityScorer,
    PCALatentMapper,
    StyleGAN2Engine,
    resolve_pca_artifact_path,
)

logger = logging.getLogger("soulmate")

REPO_ROOT = Path(__file__).resolve().parent.parent
INDEX_HTML = REPO_ROOT / "frontend" / "templates" / "index.html"
STATIC_DIR = REPO_ROOT / "frontend" / "static"
DEFAULT_FLAG_PATH = REPO_ROOT / "flag.txt"

MAX_U_COMPONENTS = 64
SEED_MAX = 2**31 - 1


def _flag_path() -> Path:
    return Path(os.environ.get("FLAG_PATH", str(DEFAULT_FLAG_PATH)))


generator_engine: Optional[StyleGAN2Engine] = None
celebrity_scorer: Optional[CelebrityScorer] = None
pca_mapper: Optional[PCALatentMapper] = None


@asynccontextmanager
async def lifespan(app: FastAPI):
    global generator_engine, celebrity_scorer, pca_mapper
    generator_engine = StyleGAN2Engine(checkpoint_dir="checkpoints")
    celebrity_scorer = CelebrityScorer()
    pca_path = resolve_pca_artifact_path()
    if pca_path.exists():
        pca_mapper = PCALatentMapper(pca_path)
    else:
        logger.warning("PCA missing")
    yield
    del generator_engine
    del celebrity_scorer
    if pca_mapper is not None:
        del pca_mapper


app = FastAPI(
    title="Soulmate Finder",
    lifespan=lifespan,
    docs_url=None,
    redoc_url=None,
    openapi_url=None,
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["GET", "POST"],
    allow_headers=["*"],
)


def _pil_to_png_bytes(img: Image.Image) -> bytes:
    buf = io.BytesIO()
    img.save(buf, format="PNG")
    buf.seek(0)
    return buf.getvalue()


MSG_INELIGIBLE = "We have determined this candidate's soulmate is not likely to be Tom Cruise....Good luck in your continued search! "
MSG_SUCCESS = "Your candidate has immense potential! Thank you for your service to the church—Your flag is here:"


def _evaluate_tom_against_threshold(tom: float) -> Dict[str, object]:
    threshold = float(TOM_SCORE_THRESHOLD)
    success = tom >= threshold
    out: Dict[str, object] = {
        "success": success,
        "tom_score": tom,
        "threshold": threshold,
    }
    if not success:
        out["message"] = MSG_INELIGIBLE
        return out

    flag_path = _flag_path()
    if not flag_path.is_file():
        logger.error("flag missing")
        raise HTTPException(
            503,
            "Challenge flag is not configured on the server.",
        )
    try:
        flag_text = flag_path.read_text(encoding="utf-8").strip()
    except OSError:
        logger.error("flag read failed")
        raise HTTPException(503, "Could not read challenge flag.") from None

    out["message"] = MSG_SUCCESS
    out["flag"] = flag_text
    return out


@app.get("/")
async def index():
    if not INDEX_HTML.is_file():
        raise HTTPException(503, "Service unavailable.")
    return FileResponse(INDEX_HTML, media_type="text/html")


class SubmitURequest(BaseModel):
    model_config = ConfigDict(extra="forbid")

    u: Annotated[List[float], Field(min_length=1, max_length=MAX_U_COMPONENTS)]
    include_image: bool = True

    @field_validator("u")
    @classmethod
    def _u_finite(cls, v: List[float]) -> List[float]:
        for x in v:
            if not math.isfinite(float(x)):
                raise ValueError("non-finite")
        return v


@app.get("/health")
async def health():
    fp = _flag_path()
    out: Dict[str, object] = {
        "status": "ok",
        "stylegan2_loaded": generator_engine is not None,
        "celebrity_scorer_loaded": celebrity_scorer is not None,
        "pca_mapper_loaded": pca_mapper is not None,
        "tom_score_threshold": TOM_SCORE_THRESHOLD,
        "flag_configured": fp.is_file(),
    }
    if pca_mapper is not None:
        info = pca_mapper.info()
        out["control_dim"] = info["control_dim"]
        out["latent_dim"] = info["latent_dim"]
        out["num_ws"] = info["num_ws"]
        out["w_dim"] = info["w_dim"]
        out["u_lower"] = info["u_lower"]
        out["u_upper"] = info["u_upper"]
    return out


@app.get("/generate-random")
async def generate_random(
    seed: int = Query(0, ge=0, le=SEED_MAX),
    truncation_psi: float = Query(0.7, ge=0.0, le=1.0),
):
    if generator_engine is None:
        raise HTTPException(503, "StyleGAN model not loaded yet")
    if celebrity_scorer is None:
        raise HTTPException(503, "Celebrity scorer model not loaded yet")

    image, _ = generator_engine.generate_random(seed=seed, truncation_psi=truncation_psi)
    image_bytes = _pil_to_png_bytes(image)
    result = celebrity_scorer.score_image(image)
    tom = float(result["tom_score"])

    payload = _evaluate_tom_against_threshold(tom)
    payload["image_base64"] = base64.standard_b64encode(image_bytes).decode("ascii")
    return JSONResponse(payload)


@app.post("/submit-u")
async def submit_u(payload: SubmitURequest):
    if generator_engine is None:
        raise HTTPException(503, "StyleGAN model not loaded yet")
    if celebrity_scorer is None:
        raise HTTPException(503, "Celebrity scorer model not loaded yet")
    if pca_mapper is None:
        raise HTTPException(
            503,
            "PCA not loaded.",
        )

    u = np.asarray(payload.u, dtype=np.float32)
    if u.shape != (pca_mapper.control_dim,):
        raise HTTPException(
            400,
            f"u must have length {pca_mapper.control_dim}",
        )

    # defense-in-depth after Pydantic validation
    if not np.all(np.isfinite(u)):
        raise HTTPException(400, "Invalid u")

    top_k = 5
    u_clipped = pca_mapper.clip_u(u)
    w = pca_mapper.u_to_w(u_clipped, device=generator_engine.device)
    image = generator_engine.render_from_w(w)
    result = celebrity_scorer.score_image(image, top_k=top_k)
    tom = float(result["tom_score"])

    image_b64: Optional[str] = None
    if payload.include_image:
        image_bytes = _pil_to_png_bytes(image)
        image_b64 = base64.standard_b64encode(image_bytes).decode("ascii")

    gate = _evaluate_tom_against_threshold(tom)
    base_out: Dict[str, object] = {
        **gate,
        "u": u.tolist(),
        "u_clipped": u_clipped.tolist(),
        "top_predictions": result["top_predictions"],
    }
    if image_b64 is not None:
        base_out["image_base64"] = image_b64

    return JSONResponse(base_out)


app.mount("/static", StaticFiles(directory=str(STATIC_DIR)), name="static")
