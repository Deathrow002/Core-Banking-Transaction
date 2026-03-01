# Core-Banking-Transaction

Transaction microservice for the Core Banking system. Handles deposits, withdrawals, and transfers using reactive programming.

## Features

- Deposit funds to accounts
- Withdraw funds from accounts
- Transfer between accounts
- Transaction history by account
- Reactive/non-blocking with Spring WebFlux
- Kafka integration for event-driven processing
- JWT authentication with role-based access control

## Tech Stack

- Java 17+ with Spring Boot WebFlux
- R2DBC for reactive database access
- PostgreSQL database
- Apache Kafka for messaging
- Eureka for service discovery

## API Endpoints

| Method | Endpoint | Description | Roles |
|--------|----------|-------------|-------|
| POST | `/transactions/deposit` | Deposit funds | ADMIN, MANAGER, USER |
| POST | `/transactions/withdraw` | Withdraw funds | ADMIN, MANAGER, USER |
| POST | `/transactions/transaction` | Transfer funds | ADMIN, MANAGER, USER |
| GET | `/transactions/GetTransByAccNo?AccNo={uuid}` | Get transactions by account | ADMIN, MANAGER |

## Configuration

Default port: `8083`

```properties
spring.application.name=transaction-service
eureka.client.service-url.defaultZone=http://discovery-service:8761/eureka
```

## Running Locally

```bash
./mvnw spring-boot:run
```

## Docker

```bash
docker build -t transaction-service .
docker run -p 8083:8083 transaction-service
```