# **Redis Sentinel**
---
## **Authors**
- [thinh.nguyen](mailto:nvthinh1511@gmail.com)

# Redis Sentinel Docker Example

This repo is a demo of a simple Redis high availability setup on Docker,
with a few tests.

## Prerequisites

* docker
* docker-compose
* /bin/bash

## Usage

Create a network for communicate of all service

	docker network create redis_network

List all network available

	docker network ls

NETWORK ID          NAME                  DRIVER              SCOPE
e863a5e32a00        redis_network         bridge              local

Start redis-sentinel using docker-compose

	docker-compose up --build --scale slave=2 --scale sentinel=3

--scale slave=2: scale 2 instance of redis-slave
--scale sentinel=3: scale 3 instance of redis-sentinel; the number of instance must be greater than quorum value


List containers

	docker-compose ps

      Name                    Command               State    Ports
--------------------------------------------------------------------
	redis_master_1     docker-entrypoint.sh redis ...   Up      6379/tcp
	redis_sentinel_1   docker-entrypoint.sh redis ...   Up      6379/tcp
	redis_sentinel_2   docker-entrypoint.sh redis ...   Up      6379/tcp
	redis_sentinel_3   docker-entrypoint.sh redis ...   Up      6379/tcp
	redis_slave_1      docker-entrypoint.sh redis ...   Up      6379/tcp
	redis_slave_2      docker-entrypoint.sh redis ...   Up      6379/tcp