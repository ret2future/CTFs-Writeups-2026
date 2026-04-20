#!/usr/bin/env python3

from __future__ import annotations

import math
import sys

import numpy as np

sys.path.append("artifacts")
from solve_nodal import transfer


def main() -> None:
    sample_omega = np.array([0.1, 0.3, 0.7, 1.1, 2.3, 5.7, 11.0], dtype=float)
    samples = 1j * sample_omega
    matrix = []
    rhs = []

    for s in samples:
        freq_hz = abs(s.imag) / (2.0 * math.pi)
        response = transfer(float(freq_hz))
        matrix.append([
            response * s**4,
            response * s**3,
            response * s**2,
            response * s,
            -s**2,
            -s,
            -1.0,
        ])
        rhs.append(-response)

    coefficients = np.linalg.lstsq(np.array(matrix, dtype=complex), np.array(rhs, dtype=complex), rcond=None)[0]
    a4, a3, a2, a1, b2, b1, b0 = coefficients
    numerator = np.array([b2, b1, b0], dtype=complex)
    denominator = np.array([a4, a3, a2, a1, 1.0 + 0.0j], dtype=complex)

    print("coefficients")
    print(a4, a3, a2, a1, b2, b1, b0)
    print("zeros")
    for zero in np.roots(numerator):
        print(zero, "hz_mag", abs(zero) / (2.0 * math.pi), "imag_hz", abs(zero.imag) / (2.0 * math.pi))
    print("poles")
    for pole in np.roots(denominator):
        print(pole, "hz_mag", abs(pole) / (2.0 * math.pi), "imag_hz", abs(pole.imag) / (2.0 * math.pi))


if __name__ == "__main__":
    main()