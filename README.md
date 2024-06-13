<h1 style="text-align: center"> Concepts Integration App </h1>
<p style="text-align: center">
    This application consists of two endpoints for getting concepts from an external website.
</p>

## Technology stack

    Backend - Kotlin

    Backend Build tool - Gradle

    Framework - Spring Boot

## URLs for endpoints

List of concepts - http://localhost:8080/api/begrep
List of concepts with page - http://localhost:8080/api/begrep?page=1
Single concept by id - http://localhost:8080/api/begrep/{id}

## Local development

In order to run the application locally, simply run

    ./gradlew bootRun


### Build application

Run the below command in your terminal to build the application:

    ./gradlew build


## OpenAPI Documentation

- Local: http://localhost:8080/swagger-ui/index.html
