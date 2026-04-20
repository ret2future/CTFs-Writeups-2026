#!/usr/bin/env python3

from __future__ import annotations

import math

import numpy as np


R1 = 91_000.0
R2 = 7_900.0
C1 = 47e-6
C2 = 4.7e-3
K1 = 1.0 + 100_000.0 / 17_000_000.0

R3 = 10_000.0
R4 = 900.0
C3 = 7e-6
C4 = 4e-6
K2 = 1.0 + 1_200_000.0 / 19_300_000.0


def transfer(freq_hz: float) -> complex:
    omega = 2.0 * math.pi * freq_hz
    s = 1j * omega

    a1 = (R1 + R2) * C2 + R1 * C1 * (1.0 - K1)
    b1 = R1 * R2 * C1 * C2
    h1 = K1 / (1.0 + a1 * s + b1 * s * s)

    a2 = R3 * C3 + R4 * C3 + R4 * C4 * (1.0 - K2)
    b2 = R3 * R4 * C3 * C4
    h2 = K2 * b2 * s * s / (1.0 + a2 * s + b2 * s * s)
    return h1 * h2


def magnitude(freq_hz: float) -> float:
    return abs(transfer(freq_hz))


def golden_search_log10(left: float, right: float, iterations: int = 200) -> float:
    phi = (math.sqrt(5.0) - 1.0) / 2.0
    c = right - phi * (right - left)
    d = left + phi * (right - left)
    fc = magnitude(10.0**c)
    fd = magnitude(10.0**d)
    for _ in range(iterations):
        if fc < fd:
            left = c
            c = d
            fc = fd
            d = left + phi * (right - left)
            fd = magnitude(10.0**d)
        else:
            right = d
            d = c
            fd = fc
            c = right - phi * (right - left)
            fc = magnitude(10.0**c)
    return 10.0 ** ((left + right) / 2.0)


def main() -> None:
    log_grid = np.linspace(-8.0, 2.0, 200_001)
    freqs = 10.0**log_grid
    mags = np.array([magnitude(float(freq)) for freq in freqs])
    peak_index = int(np.argmax(mags))
    coarse_peak = float(freqs[peak_index])
    refined_peak = golden_search_log10(math.log10(coarse_peak) - 0.05, math.log10(coarse_peak) + 0.05)
    truncated = math.floor(refined_peak * 1000.0) / 1000.0

    print(f"coarse_peak_hz={coarse_peak:.15f}")
    print(f"resonant_frequency_hz={refined_peak:.15f}")
    print(f"truncated_3dp={truncated:.3f}")


if __name__ == "__main__":
    main()