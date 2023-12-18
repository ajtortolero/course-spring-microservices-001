## Spring Boot and Postgres Application using Docker Compose (ms-user)

This project is a web application developed with Spring Boot and Java 17, connecting to a Postgres database. The application exposes a REST API for CRUD operations on User and Roles entities. Additionally, the application utilizes Docker Compose for easy deployment and execution of Spring Boot and Postgres containers.

### Requirements

To run this project, you need to have Docker and Docker Compose installed on your machine. You can follow the installation instructions at the following links:

- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Project Structure

- `docker-compose.yml`: This file defines the Spring Boot and Postgres services along with their properties and dependencies.
- `Dockerfile`: This file describes how to build the Docker image for the Spring Boot application.
- `README.md`: This file contains the project documentation.
- `src/main/java`: This directory contains the source code for the Spring Boot application, organized by packages.
- `src/main/resources`: This directory contains the resources of the Spring Boot application, such as the properties file.
- `src/test/java`: This directory contains the test code for the Spring Boot application, using JUnit and Mockito.

### Run the Project

To run the project, simply execute the following command from the root directory:

```bash
docker compose up
```

This command will create Docker images for Spring Boot and Postgres and run them in containers. It will also create an internal network for communication between the containers.

You can check the status of the containers with the following command:

```bash
docker compose ps
```

You should see output similar to this:

```
NAME      IMAGE                  COMMAND                                             SERVICE   CREATED              STATUS          PORTS
db        postgres:13.1-alpine   "docker-entrypoint.sh postgres"                     db        About a minute ago   Up 42 seconds   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp
ms-user   ms-user:latest         "java -jar build/libs/ms-user-0.0.1-SNAPSHOT.jar"   ms-user   About a minute ago   Up 41 seconds   0.0.0.0:6090->6090/tcp, :::6090->6090/tcp, 8080/tcp
```

This means that the Spring Boot application is listening on port 6090, and the Postgres database is listening on port 5432.

### Test the Application

To test the application, you can use an HTTP client like Postman or curl. The application exposes the following endpoints:

- `GET /v1/user`: Get the list of all users.
- `GET /v1/user/{id}`: Get the user with the specified id.
- `POST /v1/user`: Create a new user with the data sent in the request body.
- `DELETE /v1/user/{id}`: Delete the user with the specified id.

For example, to get the list of all users, you can execute the following command:

```bash
curl http://localhost:6090/v1/user
```

You should see a response similar to this:

```json
[ { "id": "14463182", "name": "Alejandro", "lastName": "Tortolero", "email": "email@example.com", "login": "user-example", "password": "pwd.123456" } ]
```

### Stop the Project

To stop the project, simply execute the following command from the root directory:

```bash
docker compose down
```

This command will stop and remove the Spring Boot and Postgres containers, as well as the internal network created by Docker Compose.

If you also want to remove the Docker images created by the project, you can add the `--rmi all` parameter to the previous command:

```bash
docker compose down --rmi all
```

### Access API Documentation (via Swagger)

To access the API documentation, simply run the service and access the following URL: [http://localhost:6090/swagger-ui/index.html#/](http://localhost:6090/swagger-ui/index.html#/)