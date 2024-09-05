package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication(
		scanBasePackages = {"com.luv2code.springcoredemo",
							"com.luv2code.util"})

We have to write both of them ====> springcoredemo, util
If we write ONLY util, it does not throw error, but we see 404 NOT FOUND page!

@SpringBootApplication(scanBasePackages = {"com.luv2code.util"}) ====> 404 NOT FOUND, ERROR
*/
@SpringBootApplication(scanBasePackages = {"com.luv2code.springcoredemo", "com.luv2code.util"})
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
