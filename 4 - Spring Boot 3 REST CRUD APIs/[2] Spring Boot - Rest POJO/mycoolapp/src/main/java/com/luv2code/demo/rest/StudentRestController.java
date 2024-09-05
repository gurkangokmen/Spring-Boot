package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
        System.out.println(theStudents);
        return theStudents; //Jackson will convert List<Student> to JSON array


        // Data binding is the process of converting JSON data to a Java POJO, or Java POJO to a JSON data.

        // Also known as
        //  Mapping
        //  Serialization / Deserialization
        //  Marshalling / Unmarshalling

        // Spring uses the Jackson Project behind the scenes
        // Jackson handles data binding between JSON and Java POJO

        // Spring Boot Starter Web automatically includes dependency for jackson

        // Note: Jackson calls the setXXX,getXXX methods.
        // If setXXX,getXXX does not exist, it throw No serializer found ERROR


    }
}







