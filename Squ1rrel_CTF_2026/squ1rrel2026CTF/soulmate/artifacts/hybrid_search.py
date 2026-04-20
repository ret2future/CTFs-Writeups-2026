#!/usr/bin/env python3

from __future__ import annotations

import json
import math
import random
import time
from pathlib import Path

import requests


BASE_URL = "https://soulmate.squ1rrel.dev"
LOWER = [-23.97866439819336, -22.866783142089844, -19.048107147216797, -18.220474243164062, -17.317590713500977, -15.015393257141113, -14.231842994689941, -12.789846420288086]
UPPER = [23.97866439819336, 22.866783142089844, 19.048107147216797, 18.220474243164062, 17.317590713500977, 15.015393257141113, 14.231842994689941, 12.789846420288086]
SEED = [0.0, 0.0, 8.0, -1.0, 1.0, 9.0, 0.0, 0.0]
OUT_PATH = Path("artifacts/hybrid_best.json")


def clamp_vec(vector: list[float]) -> list[float]:
    return [max(lo, min(hi, value)) for value, lo, hi in zip(vector, LOWER, UPPER)]


def evaluate(vector: list[float]) -> dict:
    response = requests.post(
        f"{BASE_URL}/submit-u",
        json={"u": vector, "include_image": False},
        timeout=30,
    )
    response.raise_for_status()
    payload = response.json()
    payload["u"] = vector[:]
    return payload


def main() -> None:
    random.seed(20260418)
    best = evaluate(SEED)
    best_score = float(best["tom_score"])
    current = SEED[:]
    sigma = [6.0, 6.0, 5.0, 5.0, 5.0, 4.0, 4.0, 4.0]
    OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
    print("start", best_score, current)

    for iteration in range(1, 401):
        if iteration % 40 == 0:
            sigma = [max(0.5, value * 0.8) for value in sigma]

        if iteration % 5 == 0:
            center = best["u"]
        else:
            center = current

        trial = clamp_vec([
            center[index] + random.gauss(0.0, sigma[index])
            for index in range(8)
        ])
        result = evaluate(trial)
        score = float(result["tom_score"])

        if score >= float(current and best_score) - 0.002:
            current = trial

        if score > best_score:
            best = result
            best_score = score
            current = trial
            OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
            print("new_best", iteration, best_score, current)
            if result.get("success"):
                print("success", result.get("flag"))
                return

        if iteration % 20 == 0:
            print("progress", iteration, best_score, sigma)
            time.sleep(0.1)

    print("final", best_score, best["u"])


if __name__ == "__main__":
    main()