# Employee Records Management System
## Description
Employee Records Management System to centralize the management of employee data across departments.

## Pre requisites
- Java 17
- PostgreSql

## Setup Guide

1. Clone the repo and change the application.properties file
  - Create a database in your DB server with the name:
      ```
        user-crud-db
      ```
  - Change the \<username\> to your Postgress Username
  - Change the \<password\> to your Postgress Password
  - Set the JWT secret Key

### Optionnal

If you have already a setup for the user, you have just to set those variables using (export/set) depending on you operating system:

    ```
       SERVER_PORT=
       DB_HOST=
       DB_PORT=
       DB_NAME=
       DB_USERNAME=
       DB_PASSWORD=
       JWT_SECRET_KEY=your_custom_secret_key
       JWT_EXPIRATION=your_custom_expiration
    ```
    
2. Go to root directory that is ~/crud-user and run the following command

    ```
      ./mvn spring-boot:run
    ```

If Step 1 and 2 are properly configured, you will see **Tomcat started on port(s): 8081**. Now use the Postman collection mentioned at bottom to explore the APIs.



## Project Architecture
1. MVC pattern is used to seggregate the functionality,and the view part is not being used for now.
2. A dedicated Repository folder is used to change the datasource with minimal changes. 
3. Outline of Core logic folder is as follows : 
  ```
  -- CrudUserApplication
    |-- CrudUserApplication.java
    |-- Auth
    |-- Config
    |-- Controller
    |-- DAO
    |   |-- Criteria
    |   |-- Specification
    |-- DTOs  
    |-- Models
    |-- Repositories
    |-- Service
    |-- Test
    -- crud-user.postman_collection
  ```

### Want to quickly try. Use Postman Collection 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/30212780/2sAYQamr4J)

## Other information
This project need a service consumer which is the front end project that you will find in the same organisation.