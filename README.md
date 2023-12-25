
# REST API for Matrimony application - Spring Boot

Welcome to the Matrimony App REST API project built with Spring Boot! This project serves as the backend for a Matrimony application, providing endpoints for creating, retrieving, updating, and deleting user profiles.

## Table of Contents
- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Front End](#front-end)

## Project Overview

This project showcases the implementation of a Matrimony App REST API using Spring Boot and Java. It includes **CRUD operation** features such as profile creation, retrieval, updating, and deletion, along with advanced filtering options.

## Getting Started

### Prerequisites

Make sure you have the following installed on your system:
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)
- An Integrated Development Environment (IDE) like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/ide/)

### Installation

 Clone the repository :
   `git clone https://github.com/MohamedThoufeeq/Nikkah`

Open the project in your preferred IDE.

Build the project:

``` 
mvn clean install
```

Run the application:
```
mvn spring-boot:run
```
The API will be accessible at `http://localhost:8080/api`.

### Project Structure
The project structure follows common Spring Boot conventions. Key components include:

- `src/main/java/com/nikkah/app/controller`: Contains the REST API controllers.
- `src/main/java/com/nikkah/app/model`: Defines the MatrimonyProfile entity and response classes.
- `src/main/java/com/nikkah/app/repo`: Includes the MatrimonyProfileRepository interface and its implementation.
- `src/main/java/com/nikkah/app/service`: Houses the MatrimonyProfileService class.
- `src/main/resources`: Contains application properties and database configurations.

### Usage

## API Endpoints
#### Create a new Matrimony Profile: 
- Http Method - `POST`
- Endpoint - `/api`
- Header : `Content-Type : application/json`
- Response Status - `201 created`

#### Retrieve all Matrimony Profiles:
- Http Method - `GET`
- Endpoint - `/api`
- Response Status - `200 ok`

#### Retrieve a specific Matrimony Profile by ID:
- Http Method - `GET`
- Endpoint - `/api/{id}`
- Response Status - `200 ok`

#### Update a Matrimony Profile:
- Http Method - `PUT`
- Endpoint - `/api/{id}`
- Response Status - `200 ok`
- Header : `Content-Type : application/json`

#### Delete a Matrimony Profile:
- Http Method - `DELETE`
- Endpoint - `/api/{id}`
- Response Status - `200 ok`
- Response Message - `Deleted Successfully`
#### Filter by Field and value
- Http Method - `GET`
- Endpoint - `/api/filter/{field}/{value}`
- Response Status - `200 ok`
- Example: `/api/filter/education/Engineering`

#### Filter profiles by multiple fields and values:
- Http Method - `GET`
- Endpoint - `/api/filter?field1=value1&field2=value2`
- Response Status - `200 ok`
- Example: `/api/filter?occupation=software&income=1000USD`

### database connections
For the dev purpose, i used **h2** in memory database. You can use any db.
Make sure you change the `application.properties` file as per your db.

### Front End
- We can integreate Front End for Matrimony application inside the Spring Boot itself.
- inside the `src/main/resources/static` and `src/main/resources/templates` we can have the front end HTML, CSS & JS files.
- *Thymeleaf* dependecy has been added already which will be usefor front end part.

#### Contributing
Contributions are welcome! Feel free to fork the repository, create a branch, and submit a pull request.
