# yolo

## Challenge Summary
- Given: a YOLOv7 demo site with `/api/detect` and `/api/model/build`, plus the shipped app source and helper binary.
- Goal: recover the protected flag from the live service.
- Constraints: the app runs as `ctf`, while `/flag.txt` is only readable by root.

## Initial Recon / Triage
- Observations:
	- `/api/model/build` saves an uploaded `.pt` file and calls `torch.load(path, map_location="cpu")` before any structural validation.
	- The container installs `/app/yolo_status` as setuid root and keeps `/flag.txt` root-only.
	- `yolo_status` reads `/flag.txt` before dropping privileges, then does `printf(stackbuf)` on attacker-controlled data built from `argv[1]`.
- File identification:
	- `starting_files/extracted/server.py`
	- `starting_files/extracted/Dockerfile`
	- `starting_files/extracted/yolo_status`
- Entry points:
	- `POST /api/model/build`
	- `/app/yolo_status <subcommand>`

## Hypotheses & Approach
- Hypothesis 1: uploading a malicious checkpoint gives code execution during `torch.load()`.
- Hypothesis 2: the setuid helper can be used to read `/flag.txt` and leak it via its format-string bug after privilege drop.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/yolo
python3 artifacts/solve.py local
```

Results:
- Local validation confirmed the checkpoint gadget executes and that `%41$s` on `yolo_status` leaks the preloaded `/flag.txt` buffer.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/yolo
python3 artifacts/solve.py remote
```

Results:
- The live service returned the helper output inside `pretrained validation failed`, exposing the flag.

## Artifacts Produced
- `artifacts/solve.py`
- `artifacts/payload.pt`

## Flag
```
squ1rrel{y0u_0nly_fl@g_1nce_5d7fb1a}
```
