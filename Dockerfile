# Build Stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

RUN apk add --no-cache git

WORKDIR /app

# Clone the Transaction service from GitHub
RUN git clone https://github.com/Deathrow002/Core-Banking-Transaction.git .

# Build the Transaction service
RUN mvn clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jre-jammy

RUN apt-get update && \
	DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends wget curl && \
	apt-get upgrade -y && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar transaction-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
