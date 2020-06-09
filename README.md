# NASK - Interview assignment
## Overview
Interview assignment for NASK. The application allows to browse the Star Wars universe through a REST API.

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
#### Option 1
You can import and run the application using your favorite IDE. 

#### Option 2
Use the following commands to run the application with Docker:

<pre>
$ docker build -t weronika-nask .
$ docker run -p 8081:8081 weronika-nask
</pre>

#### Option 3
Using Maven with the following command:

<pre>
mvn spring-boot:run
</pre>



### Cache
In order to create responses with all the data from Swapi, this application
performs many requests to the external Swapi endpoints. This is an obvious bottleneck.
To avoid performance drop and Swapi flooding there is a cache introduced for methods calling Swapi endpoints.
Since data related to Star Wars characters is not volatile, 
this cache may be having a very long time to live, probably even infinite. 
This can be set by caffeine properties in application.properties 

### Swager UI
The application supports swagger endpoint for visualising our API.
To reach swagger when the application is running visit:
http://localhost:8080/swagger-ui.html

### Docker
This application is provided with Dockerfile allowing to easily create an application docker image.
<pre>
$ docker build -t weronika-nask .
</pre>

### Prometheus
This application supports Prometheus monitoring.
To reach prometheus when the application is running visit:
http://localhost:8080/actuator/prometheus
