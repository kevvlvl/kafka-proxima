# kafka-proxima project

A simple Quarkus (Microprofile) application interacting with a containerized instance of Kafka.

### Description

See Quarkus' documentation as follows: https://quarkus.io/guides/kafka

### How to run it 

1. Start Kafka using the docker-compose file
2. compile the application: mvn clean install
3. Start the application using ./mvnw quarkus:dev

### Testing it

1. Open your browser to http://localhost:8080/dashboard.html where we read from two kafka topics
2. To update countries (push add/remove changes & update kafka topic), use the following POSTs:
 
* curl -X POST -H 'Content-Type: text/plain' http://localhost:8080/countries -d "USA"
* curl -X POST -H 'Content-Type: text/plain' http://localhost:8080/countries -d "Canada"
* curl -X POST -H 'Content-Type: text/plain' http://localhost:8080/countries/remove -d "USA"

### Health Endpoints

Microprofile Health endpoints

* http://localhost:8080/health/live - The application is up and running.
* http://localhost:8080/health/ready - The application is ready to serve requests.
* http://localhost:8080/health - Accumulating all health check procedures in the application.
