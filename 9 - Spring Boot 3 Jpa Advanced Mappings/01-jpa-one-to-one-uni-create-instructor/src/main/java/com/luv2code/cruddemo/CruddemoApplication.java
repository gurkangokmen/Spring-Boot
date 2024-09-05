package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// From the Spring Boot Framework
	// Executed after the Spring Beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) { //Inject the AppDAO (AppDAOImpl)

		// Executed after the Spring Beans have been loaded
		// Your custom code
		return runner -> { // Java lambda expression
			System.out.println("Hello, World!");
			System.out.println("⭐Important Note:⭐ \nInserts the associated entity first: InstructorDetail\nThen inserts: Instructor \n\nDue to relationship of foreign key\nInstructor needs to know the id of theInstructorDetail\n----------------------------");

			//
			// @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) ==> Same Result with EAGER, ADD Instructor and It's Detail
			//
			// It does not affect add operation.
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}








