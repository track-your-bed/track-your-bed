#!/usr/bin/env bash

(cd .. && ./package.sh trackyourbed)
(cd ../flyway && ./build_flyway_image.sh trackyourbed-flyway)
docker-compose up
