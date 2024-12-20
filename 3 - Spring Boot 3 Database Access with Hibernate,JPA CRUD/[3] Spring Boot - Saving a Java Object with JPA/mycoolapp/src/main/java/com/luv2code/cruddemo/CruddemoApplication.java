package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) { //Inject the StudentDAO

		return runner -> {
			createStudent(studentDAO);



		};
	}



	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
		tempStudent.setId(10);
		System.out.println(tempStudent);

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		System.out.println(tempStudent);
		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}







