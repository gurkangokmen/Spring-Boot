package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity //It is typically used to mark a Java class as an entity, representing a table in a relational database.
@Table(name="student") //it works if this line does not exist. (lower_case_table_names=1 at mySQL)
public class Student {

    // define fields
    @Id //if this line does not exist, it gives error (every '@Entity' class must declare or inherit at least one '@Id' or '@EmbeddedId' property)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This strategy relies on the database's built-in auto-increment feature to generate unique values for the id column. If this line does not exist, it does not give error(so save database), but when we print student we see id is 0!
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // define constructors
    public Student() {

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // define toString() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
