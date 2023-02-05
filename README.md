# java-microservices

## aim:
This repo sought to replicate a POS system in a microservice pattern

## built:
Api-Gateway
  - Using Spring Cloud Gateway
  - Using Keycloak as auth server

Discovery Server
  - Service discovery using Netflix-Eureka

Inventory Service
  - Inventory API to check if product is in stock
  - MySql DB
  - Postgres DB on Docker

Order Service
  - Order API synchronous communication with Inventory Service 
    - Implemented Circuit Breaker with Resilience4j
  - Order API asynchronous communication with Notification Service
    - Implemented Kafka 
    - Kafka producer
  - MySql DB
  - Postgres DB on Docker

Product Service
  - Product API to create and get products
  - Mongo DB

Notification Service
  - Kafka consumer
  
Add-ons
  - Implemented Distributed Tracing with Brave and Zipkin
  - Dockerized application
  - Implemented Prometheus and Grafana for monitoring

## Update and push to Docker registry:
```bash
mvn clean compile jib:build
```

## Run project:
```bash
docker compose up -d
```
