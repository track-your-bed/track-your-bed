#!/usr/bin/env bash

(cd ../flyway && ./build_flyway_image.sh)
(cd .. && ./package.sh)
docker-compose up
