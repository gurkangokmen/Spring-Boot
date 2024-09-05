package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**

 @Repository Annotation is a specialization of @Component annotation which is used to
 indicate that the class provides the mechanism for storage, retrieval, update, delete and search
 operation on objects.

 @Repository: Used to perform database operations.
 It is often used to mark classes that handle data access and storage,
 such as classes that implement data access objects (DAOs) or use the Java API (JPA) to access a database.
 <p><p>
 @Note: @Repository, @Component, @Service, @Controller annotations are working in this example.
 <p>@Repository, @Service, @Controller are specializations of @Component annotation so that.
*/

// @Repository, @Component, @Service, @Controller annotations are working in this example.
@Repository // Go to Implementation for details --Hint: it is Component also! :D
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager; //JPA Entity Manager is the main component for saving/retrieving entities

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional //Handles transaction management
    public void save(Student theStudent) {
        entityManager.persist(theStudent); //Save theJava object
    }

}










