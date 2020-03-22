#!/usr/bin/env bash

(cd .. && ./package.sh)
(cd ../flyway && ./build_flyway_image.sh)
docker-compose up
