#!/usr/bin/env bash

if [ $# -ne 1 ]; then
  echo usage: $0 [image tag]
  exit 1
fi

IMAGE_TAG=$1

mkdir -p migration_tmp
cp ../src/main/resources/db/migration/*.sql migration_tmp/
docker build . -t $IMAGE_TAG
rm -r migration_tmp
