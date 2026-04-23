# Order and Inventory Microservices Capstone Project

This is a distributed microservices capstone project built with Java, Spring Boot, Spring Cloud, and Maven.

## Services

- **Eureka Server** (Port 8761): Service discovery
- **Config Server** (Port 8888): Centralized configuration
- **API Gateway** (Port 8080): API gateway with JWT validation
- **Auth Service** (Port 8081): Authentication service
- **Order Service** (Port 8082): Order management
- **Product Service** (Port 8083): Product management

## Features

- Service discovery with Eureka
- Centralized configuration with Config Server
- API Gateway with Spring Cloud Gateway
- JWT-based authentication and authorization
- Load balancing between services

## Prerequisites

- Java 17
- Maven 3.6+

## Running the Project

1. Start Eureka Server:
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. Start Config Server:
   ```bash
   cd config-server
   mvn spring-boot:run
   ```

3. Start other services in any order:
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```
   ```bash
   cd auth-service
   mvn spring-boot:run
   ```
   ```bash
   cd order-service
   mvn spring-boot:run
   ```
   ```bash
   cd product-service
   mvn spring-boot:run
   ```

## API Endpoints

- **Auth Service**: `POST /auth/login` - Login with username/password
- **Order Service**: `GET /orders` - Get orders (requires JWT)
- **Product Service**: `GET /products` - Get products (requires JWT)

All requests to order and product services should go through the API Gateway at port 8080.

## Testing

Use Postman or curl to test the endpoints. First, login via auth service to get a JWT token, then use the token in the Authorization header for protected endpoints.