#!/usr/bin/env python3

from __future__ import annotations

import random

import requests


BASE_URL = "https://soulmate.squ1rrel.dev"


def probe_random() -> None:
    print("[random seeds]")
    best_score = -1.0
    best_seed = None
    seeds = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 42, 69, 420, 1337, 20260418]
    seeds.extend(random.randint(0, 2**31 - 1) for _ in range(20))
    for seed in seeds:
        response = requests.get(
            f"{BASE_URL}/generate-random",
            params={"seed": seed, "truncation_psi": 0.7},
            timeout=30,
        )
        response.raise_for_status()
        data = response.json()
        score = float(data["tom_score"])
        if score > best_score:
            best_score = score
            best_seed = seed
            print("best", seed, score, data["success"])
            if data["success"]:
                print(data)
                return
    print("final_random_best", best_seed, best_score)


def probe_submit_u() -> None:
    print("[submit-u baselines]")
    candidates: list[tuple[str, list[float]]] = [("zero", [0.0] * 8)]
    for index in range(8):
        positive = [0.0] * 8
        positive[index] = 1.0
        candidates.append((f"unit_{index}", positive))

        negative = [0.0] * 8
        negative[index] = -1.0
        candidates.append((f"neg_unit_{index}", negative))

    for name, vector in candidates:
        response = requests.post(
            f"{BASE_URL}/submit-u",
            json={"u": vector, "include_image": False},
            timeout=30,
        )
        response.raise_for_status()
        data = response.json()
        print(name, data["tom_score"], data["success"], data["top_predictions"][:3])


if __name__ == "__main__":
    probe_submit_u()
    probe_random()