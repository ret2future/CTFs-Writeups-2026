#!/bin/bash

docker build -t mutationworld-ramadan26 .
if [ "$1" == "local" ]; then
    docker run -p 3000:3000 mutationworld-ramadan26
fi
