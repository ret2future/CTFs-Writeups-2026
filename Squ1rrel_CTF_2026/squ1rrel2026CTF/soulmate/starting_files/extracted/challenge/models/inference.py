from __future__ import annotations

import os
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

import numpy as np
import torch
from PIL import Image
from torchvision.transforms.functional import to_pil_image

from vendor import dnnlib, legacy

TOM_SCORE_THRESHOLD = 0.15
PCA_DIM = 8
DEFAULT_PCA_ARTIFACT = Path("checkpoints") / f"pca_basis_d{PCA_DIM}.npz"


def resolve_pca_artifact_path() -> Path:
    raw = os.environ.get("PCA_BASIS_PATH", "").strip()
    if not raw:
        return DEFAULT_PCA_ARTIFACT
    return Path(raw).expanduser().resolve()


@dataclass
class PCABasis:
    mean: np.ndarray
    basis: np.ndarray
    lower: np.ndarray
    upper: np.ndarray
    num_ws: int
    w_dim: int


class PCALatentMapper:
    def __init__(self, artifact_path: str | Path):
        artifact_path = Path(artifact_path)
        if not artifact_path.exists():
            raise FileNotFoundError(f"PCA artifact not found: {artifact_path}")

        payload = np.load(artifact_path, allow_pickle=False)
        self.basis = PCABasis(
            mean=payload["mean"].astype(np.float32),
            basis=payload["basis"].astype(np.float32),
            lower=payload["lower"].astype(np.float32),
            upper=payload["upper"].astype(np.float32),
            num_ws=int(payload["num_ws"]),
            w_dim=int(payload["w_dim"]),
        )

        self.latent_dim, self.control_dim = self.basis.basis.shape
        expected_latent_dim = self.basis.num_ws * self.basis.w_dim
        if self.latent_dim != expected_latent_dim:
            raise ValueError(
                f"Basis latent dim mismatch: basis={self.latent_dim}, expected={expected_latent_dim}"
            )

    def clip_u(self, u: np.ndarray) -> np.ndarray:
        return np.clip(u.astype(np.float32), self.basis.lower, self.basis.upper)

    def u_to_w_flat(self, u: np.ndarray) -> np.ndarray:
        if u.shape != (self.control_dim,):
            raise ValueError(f"Expected u shape ({self.control_dim},), got {u.shape}")
        u_clipped = self.clip_u(u)
        w_flat = self.basis.mean + self.basis.basis @ u_clipped
        return w_flat.astype(np.float32)

    def u_to_w(self, u: np.ndarray, device: str = "cpu") -> torch.Tensor:
        w_flat = self.u_to_w_flat(u)
        w = w_flat.reshape(1, self.basis.num_ws, self.basis.w_dim)
        return torch.from_numpy(w).to(device)

    def info(self) -> Dict[str, Any]:
        return {
            "control_dim": self.control_dim,
            "latent_dim": self.latent_dim,
            "num_ws": self.basis.num_ws,
            "w_dim": self.basis.w_dim,
            "u_lower": self.basis.lower.tolist(),
            "u_upper": self.basis.upper.tolist(),
        }


class CelebrityScorer:
    def __init__(
        self,
        model_name: str = "dima806/celebs_face_image_detection",
        device: str = "auto",
    ):
        if device == "auto":
            device = "cuda" if torch.cuda.is_available() else "cpu"
        self.device = device

        from transformers import AutoImageProcessor, AutoModelForImageClassification

        self.processor = AutoImageProcessor.from_pretrained(model_name)
        self.model = AutoModelForImageClassification.from_pretrained(model_name).to(device)
        self.model.eval()

        id2label = {int(k): v for k, v in self.model.config.id2label.items()}
        self.id2label = id2label
        self.tom_label_idx = self._find_tom_label_idx(id2label)

    @staticmethod
    def _normalize_label(label: str) -> str:
        return " ".join(label.strip().lower().split())

    def _find_tom_label_idx(self, id2label: Dict[int, str]) -> int:
        target = "tom cruise"
        matches = [
            idx for idx, label in id2label.items() if self._normalize_label(label) == target
        ]
        if not matches:
            raise RuntimeError("Could not find 'Tom Cruise' label in celebrity model labels.")
        return matches[0]

    @torch.no_grad()
    def score_image(self, image: Image.Image, top_k: int = 5) -> Dict[str, object]:
        inputs = self.processor(images=image, return_tensors="pt")
        inputs = {k: v.to(self.device) for k, v in inputs.items()}
        logits = self.model(**inputs).logits
        probs = torch.softmax(logits, dim=-1)[0]

        tom_score = float(probs[self.tom_label_idx].item())

        k = min(top_k, probs.shape[0])
        top_probs, top_indices = torch.topk(probs, k=k)
        top_predictions: List[Dict[str, object]] = []
        for score, idx in zip(top_probs.tolist(), top_indices.tolist()):
            top_predictions.append(
                {
                    "label": self.id2label[int(idx)],
                    "score": float(score),
                }
            )

        return {
            "tom_score": tom_score,
            "top_predictions": top_predictions,
        }


class StyleGAN2Engine:
    def __init__(
        self,
        checkpoint_dir: str = "checkpoints",
        generator_pkl: str = "stylegan2-ffhq-config-f.pkl",
        device: str = "auto",
    ):
        if device == "auto":
            device = "cuda" if torch.cuda.is_available() else "cpu"
        self.device = device
        self.force_fp32 = self.device == "cpu"
        if self.device == "cpu":
            torch.set_num_threads(1)
            torch.set_num_interop_threads(1)

        generator_path = os.path.join(checkpoint_dir, generator_pkl)
        with dnnlib.util.open_url(generator_path) as f:
            self.generator = legacy.load_network_pkl(f, map_location=device)["G_ema"].to(device)
        self.generator.eval().requires_grad_(False)

        self.z_dim = self.generator.z_dim
        self.num_ws = self.generator.num_ws
        self.w_dim = self.generator.w_dim
        self.c_dim = self.generator.c_dim

    def _conditioning(self, batch_size: int) -> Optional[torch.Tensor]:
        if self.c_dim == 0:
            return None
        return torch.zeros((batch_size, self.c_dim), device=self.device)

    @torch.no_grad()
    def map_z_to_w(self, z: torch.Tensor, truncation_psi: float = 0.7) -> torch.Tensor:
        c = self._conditioning(z.shape[0])
        if c is None:
            c = torch.empty((z.shape[0], 0), device=self.device)
        return self.generator.mapping(
            z,
            c,
            truncation_psi=truncation_psi,
            truncation_cutoff=self.num_ws,
        )

    @torch.no_grad()
    def render_from_w(self, w: torch.Tensor, noise_mode: str = "const") -> Image.Image:
        output = self.generator.synthesis(w, noise_mode=noise_mode, force_fp32=self.force_fp32)
        img = output["image"] if isinstance(output, dict) else output
        img = (img.clamp(-1, 1) + 1) / 2
        return to_pil_image(img[0].cpu())

    @torch.no_grad()
    def generate_random(
        self,
        seed: int = 0,
        truncation_psi: float = 0.7,
        noise_mode: str = "const",
    ) -> Tuple[Image.Image, torch.Tensor]:
        rng = torch.Generator(device="cpu")
        rng.manual_seed(seed)
        z = torch.randn((1, self.z_dim), generator=rng).to(self.device)
        w = self.map_z_to_w(z, truncation_psi=truncation_psi)
        image = self.render_from_w(w, noise_mode=noise_mode)
        return image, w
