#!/usr/bin/env python3

from __future__ import annotations

import json
import math
import time
from pathlib import Path

import requests


BASE_URL = "https://soulmate.squ1rrel.dev"
LOWER = [-23.97866439819336, -22.866783142089844, -19.048107147216797, -18.220474243164062, -17.317590713500977, -15.015393257141113, -14.231842994689941, -12.789846420288086]
UPPER = [23.97866439819336, 22.866783142089844, 19.048107147216797, 18.220474243164062, 17.317590713500977, 15.015393257141113, 14.231842994689941, 12.789846420288086]
START = [0.0, 0.0, 8.0, -5.0, 1.0, 5.0, 0.0, 0.0]
OUT_PATH = Path("artifacts/coordinate_best.json")


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


def clamp(value: float, index: int) -> float:
    return max(LOWER[index], min(UPPER[index], value))


def main() -> None:
    current = START[:]
    best = evaluate(current)
    best_score = float(best["tom_score"])
    print("start", best_score, current)
    OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")

    steps = [2.0, 1.0, 0.5, 0.25]
    for step in steps:
        improved = True
        while improved:
            improved = False
            for index in range(8):
                candidates = []
                for delta in (-step, step):
                    trial = current[:]
                    trial[index] = clamp(trial[index] + delta, index)
                    candidates.append(trial)

                for trial in candidates:
                    result = evaluate(trial)
                    score = float(result["tom_score"])
                    print("try", step, index, trial[index], score)
                    if score > best_score:
                        current = trial
                        best = result
                        best_score = score
                        improved = True
                        OUT_PATH.write_text(json.dumps(best, indent=2) + "\n", encoding="utf-8")
                        print("new_best", best_score, current)
                        if result.get("success"):
                            print("success", result.get("flag"))
                            return
                    time.sleep(0.1)
        print("step_done", step, best_score, current)

    print("final", best_score, current)


if __name__ == "__main__":
    main()