# POS Microservices Architecture

A learning project that implements a retail point-of-sale backend using a microservice architecture.

## Architecture

- **API Gateway** — Spring Cloud Gateway
- **Service Discovery** — Netflix Eureka
- **Authentication** — Keycloak
- **Inventory Service** — REST API with MySQL/PostgreSQL persistence
- **Order Service** — REST API with synchronous inventory communication
- **Notification Service** — asynchronous event consumer
- **Product Service** — REST API with MongoDB

## Distributed Systems Features

- Circuit breaking with **Resilience4j**
- Asynchronous messaging with **Apache Kafka**
- Distributed tracing with **Brave / Zipkin**
- Application containerization with **Docker / Docker Compose**
- Monitoring with **Prometheus and Grafana**

## Running the Project

```bash
docker compose up -d
```

To build and publish the application images with Jib:

```bash
mvn clean compile jib:build
```

## Purpose

This project was created to explore practical concepts in microservices, service discovery, API gateways, authentication, synchronous and asynchronous service communication, resilience, observability, and containerized deployment.
