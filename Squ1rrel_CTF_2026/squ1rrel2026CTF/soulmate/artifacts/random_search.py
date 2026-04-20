#!/usr/bin/env python3

from __future__ import annotations

import json
import random
import time
from pathlib import Path

import requests


BASE_URL = "https://soulmate.squ1rrel.dev"
LOWER = [-23.97866439819336, -22.866783142089844, -19.048107147216797, -18.220474243164062, -17.317590713500977, -15.015393257141113, -14.231842994689941, -12.789846420288086]
UPPER = [23.97866439819336, 22.866783142089844, 19.048107147216797, 18.220474243164062, 17.317590713500977, 15.015393257141113, 14.231842994689941, 12.789846420288086]
OUT_PATH = Path("artifacts/random_search_best.json")


def evaluate(vector: list[float]) -> dict:
    response = requests.post(
        f"{BASE_URL}/submit-u",
        json={"u": vector, "include_image": False},
        timeout=30,
    )
    response.raise_for_status()
    payload = response.json()
    payload["u"] = vector
    return payload


def random_vector() -> list[float]:
    return [random.uniform(lo, hi) for lo, hi in zip(LOWER, UPPER)]


def main() -> None:
    random.seed(20260418)
    best = evaluate([0.0] * 8)
    print("initial_best", best["tom_score"])
    OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")

    for iteration in range(1, 201):
        candidate = random_vector()
        result = evaluate(candidate)
        score = float(result["tom_score"])
        if score > float(best["tom_score"]):
            best = result
            OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
            print("new_best", iteration, score, best["u"])  # noqa: T201
        if result.get("success"):
            print("success", iteration, score, result.get("flag"))
            return
        if iteration % 10 == 0:
            print("progress", iteration, float(best["tom_score"]))
            time.sleep(0.2)

    print("final_best", float(best["tom_score"]), best["u"])  # noqa: T201


if __name__ == "__main__":
    main()