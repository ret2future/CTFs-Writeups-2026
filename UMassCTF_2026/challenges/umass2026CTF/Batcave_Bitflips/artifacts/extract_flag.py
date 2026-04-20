#!/usr/bin/python3

from pathlib import Path


BINARY_PATH = Path('/root/umass2026CTF/Batcave_Bitflips/starting_files/batcave_license_checker')


def main() -> None:
    data = BINARY_PATH.read_bytes()[0x3000:0x3180]
    expected = data[0x40:0x60]
    encrypted_flag = data[0x60:0x80]
    plaintext = bytes(left ^ right for left, right in zip(encrypted_flag, expected))
    print(plaintext.split(b'\x00', 1)[0].decode())


if __name__ == '__main__':
    main()