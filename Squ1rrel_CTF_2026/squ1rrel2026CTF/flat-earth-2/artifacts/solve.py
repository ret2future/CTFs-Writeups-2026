#!/usr/bin/env python3
from __future__ import annotations

import argparse
import json
import re
import socket
import subprocess
import sys
from pathlib import Path

from py_ecc.bn128 import add, multiply

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "starting_files"))

from challenge_params import ALPHA_G1, BETA_G2, CURVE_ORDER, DELTA, GAMMA, GAMMA_ABC_G1, NUM_ROUNDS


ROUND_RE = re.compile(r"public out = (0x[0-9a-fA-F]+)")


def point_to_json_g1(point):
    return {"x": hex(int(point[0].n)), "y": hex(int(point[1].n))}


def parse_g1(params_point: dict[str, str]):
    from py_ecc.bn128 import FQ

    return (FQ(int(params_point["x"], 0)), FQ(int(params_point["y"], 0)))


def forge_proof(out_val: int) -> dict[str, object]:
    gamma_abc0 = parse_g1(GAMMA_ABC_G1[0])
    gamma_abc1 = parse_g1(GAMMA_ABC_G1[1])
    ic = add(gamma_abc0, multiply(gamma_abc1, out_val % CURVE_ORDER))
    cancel_scalar = (-GAMMA * pow(DELTA, -1, CURVE_ORDER)) % CURVE_ORDER
    forged_c = multiply(ic, cancel_scalar)
    return {
        "A": ALPHA_G1,
        "B": BETA_G2,
        "C": point_to_json_g1(forged_c),
    }


def iter_round_outputs(stream):
    while True:
        line = stream.readline()
        if not line:
            return
        sys.stdout.write(line)
        sys.stdout.flush()
        match = ROUND_RE.search(line)
        if match:
            yield int(match.group(1), 16)


def solve_remote(host: str, port: int) -> str:
    transcript: list[str] = []
    with socket.create_connection((host, port), timeout=20) as sock:
        sock_file = sock.makefile("rw", encoding="utf-8", newline="\n")
        rounds_solved = 0
        for out_val in iter_round_outputs(sock_file):
            proof = forge_proof(out_val)
            sock_file.write(json.dumps({"proof": proof}) + "\n")
            sock_file.flush()
            rounds_solved += 1
            if rounds_solved >= NUM_ROUNDS:
                break

        for line in sock_file:
            transcript.append(line)
            sys.stdout.write(line)
            sys.stdout.flush()

    return "".join(transcript)


def solve_local() -> str:
    proc = subprocess.Popen(
        [sys.executable, str(ROOT / "starting_files" / "server.py")],
        cwd=ROOT,
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
        bufsize=1,
    )
    assert proc.stdin is not None
    assert proc.stdout is not None

    transcript: list[str] = []
    rounds_solved = 0
    for out_val in iter_round_outputs(proc.stdout):
        proof = forge_proof(out_val)
        proc.stdin.write(json.dumps({"proof": proof}) + "\n")
        proc.stdin.flush()
        rounds_solved += 1
        if rounds_solved >= NUM_ROUNDS:
            break

    for line in proc.stdout:
        transcript.append(line)
        sys.stdout.write(line)
        sys.stdout.flush()

    proc.wait(timeout=5)
    return "".join(transcript)


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(description="Forge proofs for flat-earth-2.")
    parser.add_argument("--host", default="challs.squ1rrel.dev")
    parser.add_argument("--port", type=int, default=5004)
    parser.add_argument("--local", action="store_true", help="Run against the local server.py instead of the remote service")
    return parser


def main() -> int:
    args = build_parser().parse_args()
    if args.local:
        solve_local()
    else:
        solve_remote(args.host, args.port)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())