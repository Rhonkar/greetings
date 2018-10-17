## Greetings Dual Container App Sample

This is a very reduced sample for a multi-container application. It was created in Java with [Spring](https://spring.io/) [Boot](https://spring.io/projects/spring-boot), built using [Maven](https://maven.apache.org/) and hosted in [Tomcat](https://tomcat.apache.org/). The project structures were created with the [Spring Boot CLI](https://docs.spring.io/spring-boot/docs/current/reference/html/cli-using-the-cli.html).

* The application consists of a (server rendered) web application (``myapp``), backed by a RESTful web service (``myapi``), that are hosted in different containers, with the ``myapp`` container calling the ``myapi`` container.
* The following container base images are used:
    * [maven:3.3-jdk-8](https://hub.docker.com/_/maven/)
    * [tomcat:7.0-jre8-alpine](https://hub.docker.com/r/_/tomcat/) 
* The app code is loosely based on these samples:
    * [Serving Web Content with MVC](https://spring.io/guides/gs/serving-web-content/)
    * [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
    * [REST Service with MVC](https://spring.io/guides/gs/actuator-service/)
    * [Consuming REST](https://spring.io/guides/gs/consuming-rest/)