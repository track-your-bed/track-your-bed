#!/usr/bin/env bash

if [ $# -ne 1 ]; then
  echo usage: $0 [image tag]
  exit 1
fi

IMAGE_TAG=$1

mvn clean package spring-boot:repackage
docker build . -t $IMAGE_TAG

