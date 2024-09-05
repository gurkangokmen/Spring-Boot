package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // it provides a set of methods to perform CRUD (Create, Read, Update, Delete) operations on entities
    // JpaRepository<Entity, Primary Key>
    // that's it ... no need to write any code LOL!

    // JpaRepository<Entity, Primary Key> is compulsory for operation that Spring Data REST will automatically expose CRUD operations for <YourEntity> through RESTful endpoints.

}
