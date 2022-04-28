#!/bin/bash

mvn clean package -DskipTests
docker-compose build
docker-compose create
docker-compose start
