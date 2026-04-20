#!/bin/bash

LISTEN_PORT=50001
CHALLENGE_PATH="/challenge/jail.py"

while :
do
    su user -c "exec socat TCP-LISTEN:${LISTEN_PORT},reuseaddr,fork EXEC:'$CHALLENGE_PATH,stderr'";
done
