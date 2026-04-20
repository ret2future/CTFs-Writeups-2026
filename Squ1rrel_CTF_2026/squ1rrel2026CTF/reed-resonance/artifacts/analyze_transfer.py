#!/usr/bin/env python3

from __future__ import annotations

import math
import sys

import numpy as np

sys.path.append('artifacts')
from solve_nodal import transfer


def main() -> None:
    sample_freqs = [0.01, 0.012629554516099767, 0.3558393289594827, 0.830253258895, 5.216635077527033, 10.025819032090295]
    print('sample_magnitudes')
    for freq in sample_freqs:
        print(f'{freq:.15f} {abs(transfer(freq)):.18e}')

    freqs = np.logspace(-5, 3, 800000)
    mags = np.abs(np.array([transfer(float(freq)) for freq in freqs]))
    peak_index = int(np.argmax(mags))
    peak_freq = float(freqs[peak_index])
    peak_mag = float(mags[peak_index])
    print('peak_hz', f'{peak_freq:.15f}')
    print('peak_rad_s', f'{(2.0 * math.pi * peak_freq):.15f}')
    print('peak_mag', f'{peak_mag:.18e}')

    target = peak_mag / math.sqrt(2.0)
    left_candidates = np.where(mags[:peak_index] <= target)[0]
    right_candidates = np.where(mags[peak_index:] <= target)[0]
    if left_candidates.size:
        left = left_candidates[-1]
        print('left_3db_bracket', f'{freqs[left]:.15f}', f'{freqs[left + 1]:.15f}')
    if right_candidates.size:
        right = peak_index + right_candidates[0] - 1
        print('right_3db_bracket', f'{freqs[right]:.15f}', f'{freqs[right + 1]:.15f}')


if __name__ == '__main__':
    main()