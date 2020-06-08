# NASK - Interview assignment
## Overview
Interview assigment for NASK. The application allows to browse the Star Wars universe through a REST API.

## Technologies
* Java JDK 11
* Spring
* Maven
* JUnit
* Mockito
* Docker
* Swagger
* Prometheus

## Setup
You can either import and run the application using your favorite IDE or use the following commands.

Build an executable .jar-file with Maven:

<pre>
./mvnw package
</pre>

Then run the application:

<pre>
java -jar ./target/starwars-0.0.1-SNAPSHOT.jar
</pre>

### Swager UI
Visit: http://localhost:8080/swagger-ui.html

### Docker
This application is set on Docker. 

### Prometheus
This application is ready to be monitored by Prometheus. 
Visit:

