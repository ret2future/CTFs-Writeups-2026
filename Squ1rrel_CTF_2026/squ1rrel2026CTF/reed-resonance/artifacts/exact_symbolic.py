#!/usr/bin/env python3

from __future__ import annotations

import sympy as sp


def main() -> None:
    s = sp.symbols('s')
    vin = sp.symbols('vin')
    va, vb, v1, vc, vd, vout = sp.symbols('va vb v1 vc vd vout')

    r1 = sp.Rational(91_000)
    r2 = sp.Rational(7_900)
    c1 = sp.Rational(47, 10**6)
    c2 = sp.Rational(47, 10**4)
    k1 = sp.Integer(1) + sp.Rational(100_000, 17_000_000)

    r3 = sp.Rational(10_000)
    r4 = sp.Rational(900)
    c3 = sp.Rational(7, 10**6)
    c4 = sp.Rational(4, 10**6)
    k2 = sp.Integer(1) + sp.Rational(1_200_000, 19_300_000)

    equations = [
        sp.Eq((va - vin) / r1 + (va - vb) / r2 + (va - v1) * s * c1, 0),
        sp.Eq((vb - va) / r2 + vb * s * c2, 0),
        sp.Eq(v1, k1 * vb),
        sp.Eq((vc - v1) * s * c3 + (vc - vd) * s * c4 + (vc - vout) / r3, 0),
        sp.Eq((vd - vc) * s * c4 + vd / r4, 0),
        sp.Eq(vout, k2 * vd),
    ]

    matrix, rhs = sp.linear_eq_to_matrix(equations, [va, vb, v1, vc, vd, vout])
    solution = matrix.LUsolve(rhs)
    transfer = sp.together(sp.simplify(solution[5] / vin))
    num, den = sp.fraction(transfer)

    print('transfer =')
    print(transfer)
    print('numerator coefficients =')
    print(sp.Poly(sp.expand(num), s).all_coeffs())
    print('denominator coefficients =')
    print(sp.Poly(sp.expand(den), s).all_coeffs())
    print('zeros =')
    for zero in sp.nroots(sp.Poly(sp.expand(num), s), n=30, maxsteps=200):
        print(sp.N(zero, 20), 'hz_mag', sp.N(abs(zero) / (2 * sp.pi), 20), 'imag_hz', sp.N(abs(sp.im(zero)) / (2 * sp.pi), 20))
    print('poles =')
    for pole in sp.nroots(sp.Poly(sp.expand(den), s), n=30, maxsteps=200):
        print(sp.N(pole, 20), 'hz_mag', sp.N(abs(pole) / (2 * sp.pi), 20), 'imag_hz', sp.N(abs(sp.im(pole)) / (2 * sp.pi), 20))


if __name__ == '__main__':
    main()