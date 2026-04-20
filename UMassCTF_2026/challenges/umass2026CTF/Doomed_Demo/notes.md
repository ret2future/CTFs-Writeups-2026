# Doomed_Demo

## Challenge Summary
- Given: `doomed-demo.zip`, containing `demo.lmp`, `freedoom-0.13.0.zip`, and `WALKTHROUGH.txt`.
- Goal: recover the player's final X and Y coordinates and format them as `UMASS{<X><Y>}` in hexadecimal without `0x` prefixes.
- Constraints: the original demo is broken, and the free hint says to use `WALKTHROUGH.txt`.

## Initial Recon / Triage
- Observations:
	- `WALKTHROUGH.txt` identifies the game as Freedoom Phase 2 v0.13.0 on Chocolate Doom, skill 2, MAP02.
	- `demo.lmp` is not directly playable as given, but the body still looks like valid Doom input frames.
- File identification:
	- `demo.lmp`: damaged Doom demo.
	- `freedoom-0.13.0.zip`: correct IWAD source; the installed package also provides `freedoom2.wad`.
	- `WALKTHROUGH.txt`: confirms the intended map/run and that the player stood still for 15 seconds at the end.
- Entry points:
	- recover the playable demo by repairing the header.
	- use a Doom engine to replay it and read the final coordinates in the same format as Doom's `idmypos`/`mypos` cheat output.

## Hypotheses & Approach
- Hypothesis 1: the `.lmp` body is intact and only the header is corrupted.
- Hypothesis 2: once replayed under the correct IWAD/version, the engine can reveal the exact final raw fixed-point coordinates.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd starting_files
unzip -o doomed-demo.zip
xxd -g1 -l 128 demo.lmp
python3 ../artifacts/scan_demo_layout.py
python3 ../artifacts/dump_demo_frames.py 14 4 | head -40
```

Results:
- `WALKTHROUGH.txt` confirmed the level/run details and the need to recover the final coordinates.
- Frame-alignment scans showed the strongest candidate was a 4-byte Doom tic stream beginning at offset 14.
- Reconstructing the demo with a normal Doom 1.9 single-player header produced a valid playable demo.

### Stage 2
Commands:
```bash
apt-get install -y chocolate-doom
python3 artifacts/build_demo_candidates.py

# Build an instrumented Chocolate Doom that prints final coordinates in
# the same raw hex format used by the in-game mypos cheat.
cd artifacts/candidates/chocolate-doom-3.1.1
cmake -S . -B build
cmake --build build -j4

cd /root/umass2026CTF/Doomed_Demo/artifacts/candidates
xvfb-run -a ./chocolate-doom-3.1.1/build/src/chocolate-doom \
	-iwad /usr/share/games/doom/freedoom2.wad \
	-timedemo cand_10_v109.lmp -nodraw -nosound -nograbmouse -window
```

Results:
- Several repaired candidates were replayable, but overlaying their final positions on MAP02 showed `cand_10_v109.lmp` lands in the lower-right exit sequence described by the walkthrough.
- The instrumented binary printed:
	- `FINALPOS: ang=0x8000000 x=0x2a85fdd y=0xfd80024b`
- Concatenating the X and Y hex values gives the flag.

## Artifacts Produced
- `artifacts/`
- `artifacts/scan_demo_layout.py`
- `artifacts/dump_demo_frames.py`
- `artifacts/build_demo_candidates.py`
- `artifacts/candidates/cand_10_v109.lmp`
- `artifacts/candidates/chocolate-doom-3.1.1/` (instrumented source tree and build)
- `artifacts/map02.png`

## Flag
```
UMASS{2A85FDDFD80024B}
```
