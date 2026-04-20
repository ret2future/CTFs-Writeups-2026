#!/usr/bin/env python3

from __future__ import annotations

import json
from pathlib import Path

import numpy as np
import requests
from scipy.optimize import Bounds, minimize


BASE_URL = "https://soulmate.squ1rrel.dev"
LOWER = np.array(
    [-23.97866439819336, -22.866783142089844, -19.048107147216797, -18.220474243164062,
     -17.317590713500977, -15.015393257141113, -14.231842994689941, -12.789846420288086],
    dtype=np.float64,
)
UPPER = np.array(
    [23.97866439819336, 22.866783142089844, 19.048107147216797, 18.220474243164062,
     17.317590713500977, 15.015393257141113, 14.231842994689941, 12.789846420288086],
    dtype=np.float64,
)
SEED = np.array([0.0, 0.0, 8.0, -1.0, 1.0, 9.0, 0.0, 0.0], dtype=np.float64)
OUT_PATH = Path("artifacts/powell_best.json")


class FoundFlag(Exception):
    pass


def evaluate(vector: np.ndarray) -> dict:
    clipped = np.clip(vector, LOWER, UPPER)
    response = requests.post(
        f"{BASE_URL}/submit-u",
        json={"u": clipped.tolist(), "include_image": False},
        timeout=30,
    )
    response.raise_for_status()
    payload = response.json()
    payload["u"] = clipped.tolist()
    return payload


def main() -> None:
    cache: dict[tuple[float, ...], float] = {}
    best = evaluate(SEED)
    best_score = float(best["tom_score"])
    OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
    print("start", best_score, best["u"])

    def objective(x: np.ndarray) -> float:
        nonlocal best, best_score
        key = tuple(np.round(np.clip(x, LOWER, UPPER), 6))
        if key in cache:
            return cache[key]
        result = evaluate(np.array(key, dtype=np.float64))
        score = float(result["tom_score"])
        value = -score
        cache[key] = value
        if score > best_score:
            best = result
            best_score = score
            OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
            print("new_best", best_score, best["u"])
        if result.get("success"):
            print("success", result.get("flag"))
            raise FoundFlag()
        return value

    try:
        minimize(
            objective,
            SEED,
            method="Powell",
            bounds=Bounds(LOWER, UPPER),
            options={"maxiter": 60, "disp": True, "xtol": 0.25, "ftol": 1e-4},
        )
    except FoundFlag:
        return

    print("final", best_score, best["u"])


if __name__ == "__main__":
    main()