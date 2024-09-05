package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // It indicates that a class contains bean definitions and configuration instructions for a Spring application.
public class SportConfig {
    /*
      it is initialized. so it isn't lazy! ✔️
    */
    @Bean //The bean id defaults to the method name
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
