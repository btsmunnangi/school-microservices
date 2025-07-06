# School Microservices

This repository contains a set of microservices built with Spring Boot, Postgres, and Docker for managing school-related data including schools, students and review

## Services Included

- **School Service** — Manages school data  
- **Student Service** — Manages student records
- **Review Service** - Manages School Review
- **Eureka Server** — Service registry for microservices discovery  
- **Config Server** — Centralized configuration management  
- **API Gateway** — Gateway for routing API requests to microservices  

## Prerequisites

- Java 11 or above  
- Maven  
- Docker and Docker Compose  
- Postgres (can run locally or via Docker)  
- RabbitMQ (Asynchronous Message Broker)

## Build & Run

1. Clone the repository:  
   git clone https://github.com/btsmunnangi/school-microservices.git
   cd school-microservices

2. Build Docker images for each microservice:
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=bts1976/{service-name}

3.Start all services using Docker Compose:
  docker-compose up
  
  

   
