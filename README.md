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

- Java 8 or above  
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

3. Start all services using Docker Compose:
   docker-compose up
   
 ## Docker Compose Setup

This project uses Docker Compose to run all microservices and their dependencies together.

### Included Services
- PostgreSQL (Database)
- RabbitMQ (Messaging)
- Zipkin (Distributed Tracing)
- Config Server
- Service Registry (Eureka)
- API Gateway (port 8083)
- School, Student, Review Microservices

### How to Run

1. Ensure Docker and Docker Compose are installed on your machine.
2. From the root of the repository, run:
   ```bash
   docker-compose up -d
3.This will start all services and dependencies on the configured ports.

4.Access RabbitMQ UI at: http://localhost:15672
  Access Zipkin UI at: http://localhost:9411
  Access Eureka Dashboard at: http://localhost:8761
  Access Gateway at: http://localhost:8083
Notes
   Database credentials are set in the docker-compose file and can be modified as needed.

   Logs are written inside containers, access them using:

docker logs <container_name>
To stop and remove all containers, networks, and volumes:
docker-compose down -v




 
  

   
