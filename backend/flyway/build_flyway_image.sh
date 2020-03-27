#!/usr/bin/env bash

mkdir -p migration_tmp
cp ../src/main/resources/db/migration/*.sql migration_tmp/
docker build . -t trackyourbed-flyway
rm -r migration_tmp
