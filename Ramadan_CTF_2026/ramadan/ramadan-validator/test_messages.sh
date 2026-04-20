#!/bin/bash
cd /Users/elenaeftimie/Desktop/CTFs/Madalina/ramadan-validator

for test in "Congratulations!" "Congratulations" "Success!" "Success" "Great!" "Good!" "Correct!" "Well done!" "Accepted!"; do
  output=$(printf "$test" | docker run --rm --platform linux/amd64 -i -v "$PWD":/work -w /work ubuntu:24.04 ./validator 2>&1)
  if ! echo "$output" | grep -q "Sorry"; then
    echo "[!] POSSIBLE: $test -> $output"
  fi
done

echo "Test complete"
