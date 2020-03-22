#!/usr/bin/env bash

mkdir -p migration
cp ../src/main/resources/db/migration/*.sql migration/
docker build . -t trackyourbed-flyway
