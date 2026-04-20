#!/bin/bash

docker build -t giftforge-ramadan26 .
if [ "$1" == "local" ]; then
    docker run -p 5000:5000 giftforge-ramadan26
fi