#!/bin/sh

# Increase the timeout to 15 seconds for the polling loop
# and add a check for the device file before executing commands.
(
    # Wait for the system to boot and the device to appear
    echo "echo '[+] Waiting for /dev/feedback...'"
    echo "i=0"
    echo "while [ ! -e /dev/feedback ] && [ \$i -lt 15 ]; do"
    echo "  sleep 1"
    echo "  i=\$((i+1))"
    echo "done"
    echo "if [ -e /dev/feedback ]; then"
    echo "  echo '[+] /dev/feedback found. Executing payload.'"
    # Read commands from stdin and execute them
    cat
    echo "else"
    echo "  echo '[-] Timed out waiting for /dev/feedback.'"
    echo "fi"
    echo "exit"
) | ./run_test.sh
