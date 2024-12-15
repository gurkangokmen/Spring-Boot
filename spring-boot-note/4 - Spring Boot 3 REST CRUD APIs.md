# Spring Boot Revise

## 4 - Spring Boot 3 REST CRUD APIs

## Table of Contents

1. [Annotations](#annotations)
2. [Path Variable](#path-variable)
3. [Exception Handling](#exception-handling)
    * [Method Level](#method-level)
    * [Global Level](#global-level)
4. [Service Layer](#service-layer)
5. [JpaRepository](#jparepository)
6. [CrudRepository](#crudrepository)
7. [Data Rest](#data-rest)


# Annotations
```java
@RestController
@RequestMapping("/test")
@GetMapping("/hello")
@PostMapping
@PutMapping
@DeleteMapping
@PostConstruct
@ExceptionHandler
@ControllerAdvice
@Service
@Configuration
```

# Path Variable

```java
@GetMapping("/students/{studentId}")
public Student getStudent(@PathVariable int studentId) {

    return theStudents.get(studentId);
}
```

# Exception Handling

## Method Level

```java
@GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // just index into the list ... keep it simple for now

        // check the studentId again list size

        if ( (studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }



// Controller-Level Scope
// The annotated method will only handle exceptions thrown by the controller methods in the same class.

@ExceptionHandler
public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

    // create a StudentErrorResponse

    StudentErrorResponse error = new StudentErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value()); //404
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    // return ResponseEntity

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
```

## Global Level

```java
// Global Scope Exception Handler
@ControllerAdvice
public class StudentRestExceptionHandler {

    // add exception handling code here

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // add another exception handler ... to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

# Service Layer


```java
DAO (@Repository) -> Service(@Service) -> Controller(@Controller or @RestController)

Service combine a lot of DAOs.
```

# JpaRepository
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { //Entity type, Primary Key
    // that's it ... no need to write any code LOL!
}
```

# CrudRepository
```java
public interface EmployeeRepository extends CrudRepository<Employee, Integer> { //Entity type, Primary Key
    // that's it ... no need to write any code LOL!
}
```

# Data Rest

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

```
# spring.data.rest.base-path=/magic-api
# spring.data.rest.default-page-size=20
# spring.data.rest.max-page-size = 1

# max-page-size property is dominant to default-page-size
# http://localhost:8080/members?page=0&size=2 is dominant to spring.data.rest.default-page-size=20
# spring.data.rest.max-page-size = 1 is dominant to http://localhost:8080/members?page=0&size=2
```

```java
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)  {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};


        // disable HTTP methods for Employee: PUT, POST, DELETE and PATCH

        /**
         * getExposureConfiguration: Accessing the exposure configuration of the repository
         * forDomainType : Specifying that the configuration applies to the domain type (entity) 'Employee'
         * withItemExposure: Configuring item exposure for the Employee entity. This lambda expression disables the specified HTTP methods for individual items.
         * withCollectionExposure: Configuring collection exposure for the Employee entity. This lambda expression disables the specified HTTP methods for collections of items.
         */
        config.getExposureConfiguration()
                .forDomainType(Employee.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
```