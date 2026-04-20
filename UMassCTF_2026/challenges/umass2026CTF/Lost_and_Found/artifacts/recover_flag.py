#!/usr/bin/env python3

import hashlib
import re
import zlib
from pathlib import Path

BASE = Path('/root/umass2026CTF/Lost_and_Found/artifacts/mnt/root/home/5457501C')
HEAD_RELOG = BASE / '165F5E1B/3275782C'
COMMIT_60 = BASE / '1552530D16021B/4C00/43085C5844470B41040558560651474344484B5A585B5142051A445E5B590447101F1D420646'
COMMIT_55 = BASE / '1552530D16021B/4F05/1B01090D454E5F4151050D0055034D144C1B48075E5F5C4C5B1C4F580E5A514F114E41160246'
CONFIG = BASE / '195F570E1C11'

INITIAL_COMMIT = '8fadc480a8f31d2dac1099cfbb03a8bfb5de569f'
INITIAL_TREE = '1421ec4a2c9b020f5ee3f910a4626810578fe0ee'


def recover_key92() -> bytes:
    config_plain = (
        b'[core]\n'
        b'\trepositoryformatversion = 0\n'
        b'\tfilemode = true\n'
        b'\tbare = false\n'
        b'\tlogallrefupdates = true\n'
    )
    enc = CONFIG.read_bytes()
    return bytes(cipher ^ plain for cipher, plain in zip(enc, config_plain))


def recover_initial_commit_ts() -> int:
    for ts in range(1774600000, 1774735000):
        body = (
            f'tree {INITIAL_TREE}\n'.encode()
            + f'author root <root@localhost.localdomain> {ts} +0000\n'.encode()
            + f'committer root <root@localhost.localdomain> {ts} +0000\n\n'.encode()
            + b'a bunch of nonsense\n'
        )
        obj = b'commit ' + str(len(body)).encode() + b'\x00' + body
        if hashlib.sha1(obj).hexdigest() == INITIAL_COMMIT:
            return ts
    raise RuntimeError('initial commit timestamp not found')


def main() -> None:
    key92 = recover_key92()
    initial_ts = recover_initial_commit_ts()

    enc_head = HEAD_RELOG.read_bytes()
    line1 = (
        b'0' * 40
        + b' '
        + INITIAL_COMMIT.encode()
        + b' '
        + f'root <root@localhost.localdomain> {initial_ts} +0000\tcommit (initial): a bunch of nonsense\n'.encode()
    )
    key171 = bytes(cipher ^ plain for cipher, plain in zip(enc_head[: len(line1)], line1))
    if key171[:92] != key92:
        raise RuntimeError('key prefix mismatch while reconstructing HEAD reflog line 1')

    commit_60_plain = zlib.decompress(
        bytes(cipher ^ key171[i] for i, cipher in enumerate(COMMIT_60.read_bytes()))
    )
    match = re.search(rb'git stash <git@stash> (\d{10}) \+0000', commit_60_plain)
    if not match:
        raise RuntimeError('stash timestamp not found in commit 60')
    stash_ts = match.group(1).decode()

    line2 = (
        INITIAL_COMMIT.encode()
        + b' '
        + INITIAL_COMMIT.encode()
        + b' '
        + f'git stash <git@stash> {stash_ts} +0000\treset: moving to HEAD\n'.encode()
    )
    head_plain = line1 + line2
    key314 = bytes(cipher ^ plain for cipher, plain in zip(enc_head, head_plain))

    stash_commit = zlib.decompress(
        bytes(cipher ^ key314[i] for i, cipher in enumerate(COMMIT_55.read_bytes()))
    )
    flag_match = re.search(rb'UMASS\{[^}]+\}', stash_commit)
    if not flag_match:
        raise RuntimeError('flag not found in decrypted stash commit')

    print(flag_match.group(0).decode())
    print()
    print(stash_commit.decode('latin-1'))


if __name__ == '__main__':
    main()
