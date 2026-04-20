#!/bin/bash

docker build -t notey-ramadan26 .
if [ "$1" == "local" ]; then
    docker run -p 3000:3000 notey-ramadan26
fi