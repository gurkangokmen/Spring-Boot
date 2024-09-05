package com.luv2code.cruddemo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	/*
	Spring Boot iki tane interface sunar. Bunlar CommandLineRunner ve ApplicationRunner interface'idir.
	Görevleri, Boot uygulaması tamamen başladığı andan sonra bir takım kodların çalıştırılmasını sağlar.

	You can give any name method: commandLineRunner or myLovelyMethod
	 */
	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {

		return runner -> { //java lambda expression
			System.out.println("Hello, World!");;

		};
	}






}







