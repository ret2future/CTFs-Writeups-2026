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
    s = 1j * 2.0 * math.pi * freq_hz

    matrix = np.zeros((6, 6), dtype=complex)
    rhs = np.zeros(6, dtype=complex)

    # Unknown ordering: Va, Vb, V1, Vc, Vd, Vout
    # Left stage node A
    matrix[0, 0] = 1.0 / R1 + 1.0 / R2 + s * C1
    matrix[0, 1] = -1.0 / R2
    matrix[0, 2] = -s * C1
    rhs[0] = 1.0 / R1

    # Left stage node B
    matrix[1, 0] = -1.0 / R2
    matrix[1, 1] = 1.0 / R2 + s * C2

    # Left stage op-amp constraint
    matrix[2, 1] = -K1
    matrix[2, 2] = 1.0

    # Right stage node C
    matrix[3, 2] = -s * C3
    matrix[3, 3] = s * C3 + s * C4 + 1.0 / R3
    matrix[3, 4] = -s * C4
    matrix[3, 5] = -1.0 / R3

    # Right stage node D
    matrix[4, 3] = -s * C4
    matrix[4, 4] = s * C4 + 1.0 / R4

    # Right stage op-amp constraint
    matrix[5, 4] = -K2
    matrix[5, 5] = 1.0

    solution = np.linalg.solve(matrix, rhs)
    return solution[5]


def magnitude(freq_hz: float) -> float:
    return float(abs(transfer(freq_hz)))


def golden_search(left_log10: float, right_log10: float, steps: int = 250) -> float:
    phi = (math.sqrt(5.0) - 1.0) / 2.0
    c = right_log10 - phi * (right_log10 - left_log10)
    d = left_log10 + phi * (right_log10 - left_log10)
    fc = magnitude(10.0**c)
    fd = magnitude(10.0**d)
    for _ in range(steps):
        if fc < fd:
            left_log10 = c
            c = d
            fc = fd
            d = left_log10 + phi * (right_log10 - left_log10)
            fd = magnitude(10.0**d)
        else:
            right_log10 = d
            d = c
            fd = fc
            c = right_log10 - phi * (right_log10 - left_log10)
            fc = magnitude(10.0**c)
    return 10.0 ** ((left_log10 + right_log10) / 2.0)


def main() -> None:
    log_grid = np.linspace(-6.0, 3.0, 200_001)
    freqs = np.power(10.0, log_grid)
    mags = np.array([magnitude(float(freq)) for freq in freqs])
    index = int(np.argmax(mags))
    coarse = float(freqs[index])
    refined = golden_search(math.log10(coarse) - 0.05, math.log10(coarse) + 0.05)
    truncated = math.floor(refined * 1000.0) / 1000.0

    print(f"coarse_peak_hz={coarse:.15f}")
    print(f"resonant_frequency_hz={refined:.15f}")
    print(f"truncated_3dp={truncated:.3f}")


if __name__ == "__main__":
    main()