# Microservices-Demo

## Overview  
This repository contains a demo project showcasing the use of Spring Boot to build a microservices architecture. The goal of this project is to provide a simple, yet comprehensive example of how to create, deploy, and communicate between multiple microservices using Spring Boot and related technologies.

## Features
- **Microservices Architecture:** Demonstrates a typical microservices architecture with multiple services.
- **Spring Boot:** Utilizes Spring Boot to simplify the development and configuration of services.  
- **RESTful APIs:** Each service exposes RESTful endpoints for interaction.  
- **Service Discovery:** Implements Eureka for service registration and discovery.
- **Kafka:** Uses Apache Kafka for interservice communication.  
- **API Gateway:** Uses Spring Cloud Gateway as an API gateway to route requests to appropriate services.  
- **Database Support:** Each service can have its own database, demonstrated with H2.  
- **Docker Support:** Contains Dockerfiles for easy containerization of services.  

## Services
This demo consists of the following microservices:

- **Products Service:** Handles product information
- **Carts Service:** Manages carts and their items
- **API Gateway:** Single entry point for client applications
- **Service Registry:** Manages service registration and discovery

## API Endpoints
### Products Service  
**POST /api/v1/products/create:** Creates a new product  
**GET /api/v1/products/{id}:** Retrieve the product details for the given id  
**PATCH /api/v1/products/update/{id}:** Updates the product with the given id  
**DELETE /api/v1/products/{id}:** Deletes the product with the given id  
### Carts Service  
**POST /create/{userId}:** Creats a new cart for the user with the given id  
**POST /{userId}/products/{productId}:** Adds the product with the given id to the cart of the user with the given id  
**DELETE /{userId}/products/{productId}:** Deletes the product with the given id from the cart of the user with the given id  
**GET /{userId}:** Retrieve the cart details for the user with the given id  
**DELETE /{userId}:** Deletes the cart of the user with the given id

## Getting Started

### Prerequisites
- Java 21 or higher
- Git
- Docker

### Clone the Repository
    git clone https://github.com/xxSlashxx/microservices-demo
    cd microservices-demo

### Running the Application

    docker compose up

### Accessing the Services
Each service's endpoints can be accessed through the gateway. For example, to reach the product service, you might use `http://localhost:8080/api/v1/products`.

### Configuration
Configuration settings can be found in the application.properties files located in each service's src/main/resources directory. You can customize properties like database connections, port numbers, and Eureka server settings.

