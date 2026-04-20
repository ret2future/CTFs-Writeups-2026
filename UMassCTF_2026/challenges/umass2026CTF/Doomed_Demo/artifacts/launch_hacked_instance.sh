#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="/root/umass2026CTF/Doomed_Demo"
BIN="$ROOT_DIR/artifacts/candidates/chocolate-doom-3.1.1/build/src/chocolate-doom"
IWAD="$ROOT_DIR/starting_files/freedoom-0.13.0/freedoom2.wad"

# Default spawn is the manually reconstructed end-area viewpoint near the
# exit-elevator controls on MAP03.
spawn_x="${CHOC_POS_X:-0x125af27a}"
spawn_y="${CHOC_POS_Y:-0xf960a980}"
spawn_angle="${CHOC_POS_ANGLE:-0x47000000}"

usage() {
    cat <<'EOF'
Usage: launch_hacked_instance.sh [--spawn x y angle]

Starts the instrumented Chocolate Doom binary on Freedoom Phase 2 MAP03 and
teleports the player to a debug spawn point using the already-patched engine.

Options:
  --spawn x y angle   Override the default fixed-point spawn position.
  -h, --help          Show this help text.

Environment:
  DISPLAY             Must point at a real X display if you want a visible window.
  CHOC_POS_X          Override default spawn x if --spawn is not used.
  CHOC_POS_Y          Override default spawn y if --spawn is not used.
  CHOC_POS_ANGLE      Override default spawn angle if --spawn is not used.

Default spawn:
  x=0x125af27a
  y=0xf960a980
  angle=0x47000000
EOF
}

while (($# > 0)); do
    case "$1" in
        --spawn)
            if (($# != 4)); then
                echo "--spawn requires exactly three values: x y angle" >&2
                exit 1
            fi
            spawn_x="$2"
            spawn_y="$3"
            spawn_angle="$4"
            shift 4
            ;;
        -h|--help)
            usage
            exit 0
            ;;
        *)
            echo "Unknown argument: $1" >&2
            usage >&2
            exit 1
            ;;
    esac
done

if [[ -z "${DISPLAY:-}" ]]; then
    cat >&2 <<'EOF'
DISPLAY is not set.

Run this from a graphical desktop terminal, for example:
  DISPLAY=:0 /root/umass2026CTF/Doomed_Demo/artifacts/launch_hacked_instance.sh
EOF
    exit 1
fi

if [[ ! -x "$BIN" ]]; then
    echo "Chocolate Doom binary not found: $BIN" >&2
    exit 1
fi

if [[ ! -f "$IWAD" ]]; then
    echo "IWAD not found: $IWAD" >&2
    exit 1
fi

echo "Launching hacked Doomed Demo instance on DISPLAY=$DISPLAY"
echo "Spawn: x=$spawn_x y=$spawn_y angle=$spawn_angle"

export CHOC_POS_X="$spawn_x"
export CHOC_POS_Y="$spawn_y"
export CHOC_POS_ANGLE="$spawn_angle"

exec "$BIN" \
    -iwad "$IWAD" \
    -warp 3 \
    -skill 2 \
    -nomonsters \
    -window \
    -nograbmouse