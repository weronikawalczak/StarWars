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
* Lombok

## Setup
1. You can either import and run the application using your favorite IDE or use the following commands.

$ docker build -t weronika-nask .
$ docker run -p 8081:8081 weronika-nask

Build an executable .jar-file with Maven:

<pre>
./mvnw package
</pre>

Then run the application:

<pre>
java -jar ./target/starwars-0.0.1-SNAPSHOT.jar
</pre>

### Cache
In order to create responses with all the data from swapi, this application
performs many requests to the external swapi endpoints. This is an obvious bottleneck.
To avoid performance drop and swapi flooding there is a cache introduced for methods calling swapi endpoints.
Since data related to starwars characters is not volatile, 
this cache may be having a very long time to live, probably even infinite. 
This can be set by caffeine properties in application.properties 

### Swager UI
The application supports swagger endpoint for visualising our API
To reach swagger when the application is running visit:
http://localhost:8080/swagger-ui.html

### Docker
This application is provided with Dockerfile allowing to easily create an application docker image.
<pre>
$ docker build -t weronika-nask .
</pre>

### Prometheus
This application supports prometheus monitoring.
To reach prometheus when the application is running visit:
http://localhost:8080/actuator/prometheus
