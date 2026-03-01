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

## Request Examples

### Deposit
```json
POST /transactions/deposit
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "accountNo": "550e8400-e29b-41d4-a716-446655440000",
  "amount": 1000.00
}
```

### Transfer
```json
POST /transactions/transaction
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "fromAccountNo": "550e8400-e29b-41d4-a716-446655440000",
  "toAccountNo": "550e8400-e29b-41d4-a716-446655440001",
  "amount": 500.00
}
```

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