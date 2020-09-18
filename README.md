# kafka-proxima project

A simple Quarkus (Microprofile) application interacting with a containerized instance of Kafka.

### Description

See Quarkus' documentation as follows: https://quarkus.io/guides/kafka

### How to run it 

1. Start Kafka using the docker-compose file
2. compile the application: mvn clean install
3. Start the application using ./mvnw quarkus:dev