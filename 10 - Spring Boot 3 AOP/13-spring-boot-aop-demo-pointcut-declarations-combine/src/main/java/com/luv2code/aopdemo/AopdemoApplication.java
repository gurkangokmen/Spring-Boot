package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		return runner -> {

			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		//
		// Match methods in a DAO package and EXCLUDE getter/setter methods
		//

		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true); // Will match
		theAccountDAO.doWork(); // Will match

		// call the accountdao getter/setter methods
		theAccountDAO.setName("foobar"); // Will NOT match
		theAccountDAO.setServiceCode("silver"); // Will NOT match

		String name = theAccountDAO.getName(); // Will NOT match
		String code = theAccountDAO.getServiceCode(); // Will NOT match

		// call the membership business method
		theMembershipDAO.addSillyMember(); // Will match
		theMembershipDAO.goToSleep(); // Will match

	}

}








