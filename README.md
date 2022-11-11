# Board Games application

Board games orders management system which allows to create and modify products and orders.

## Main functionalities

1. Add and edit products.
2. Add and edit product orders.

## To create connection to database
Update database credentials in application.properties file on lines 11-12.

## Application testing
Swagger is used for testing the application CRUD operations (http://localhost:8080/documentation/swagger-ui.html).

## To launch web browser for application testing
In 'edit configurations' in 'modify options' selection set up 'add before launch task'.
Insert url: http://localhost:8080/documentation/swagger-ui.html

## Project structure
Domain layers:  
entity - an abstract level class to correlate with a table in the database;  
repository - to perform operations on the object in database;  
DTO - Data Transfer Object contains class properties and getters and settings methods for accessing those properties;  
controller - responsible for processing incoming REST API requests, preparing a model and returning a response;  
service - for adding business logic and functionalities;  
request - modified DTO class for getting selection of properties of the object;  
response - modified DTO class for sending selection of properties of the object;  
mapper - to interchange data between entity and DTO layers.
  
Infrastructure domain is created for exception handling classes.  
Validation domain for validation class and methods.  
  
Created db directory to create tables for database and insert initial data if needed.  
  
Code comments are created inside code.  
Created javadoc directory with index.html file to get general access to code comments.  
  
  
  
### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

