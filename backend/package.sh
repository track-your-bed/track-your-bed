#!/usr/bin/env bash

mvn clean package spring-boot:repackage
docker build . -t trackyourbed

