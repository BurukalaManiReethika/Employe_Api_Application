# Employee Management API

A RESTful Employee Management API built using Spring Boot, Spring Data JPA, MySQL, and Maven.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- H2 Database
- Maven
- JUnit 5
- Mockito

## Project Structure

```text
employee-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── reethika/
│   │   │           └── employeeapi/
│   │   │               ├── EmployeeApiApplication.java
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   └── Employee.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   └── EmployeeService.java
│   │   │               │
│   │   │               └── exception/
│   │   │                   ├── DuplicateEmailException.java
│   │   │                   ├── ResourceNotFoundException.java
│   │   │                   └── GlobalExceptionHandler.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-dev.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── reethika/
│                   └── employeeapi/
│                       └── EmployeeServiceTest.java
│
└── pom.xml
