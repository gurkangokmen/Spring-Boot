package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }


    // Restrict access based on the HTTP Request
    // Configure security of web paths in application, login, logout etc
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .anyRequest().authenticated() // Any request to the app must be authenticated  (ie logged in)
                )
                .formLogin(form -> //We are customizing the form login process
                        form
                                .loginPage("/showMyLoginPage")  // Show our custom form at the request mapping “/showMyLoginPage” ==> TO DO We need to create a controller for this request mapping “/showMyLoginPage”
                                .loginProcessingUrl("/authenticateTheUser") // Login form should POST data to this URL for processing (check user id and password) ==> No Controller Request Mapping required for this. We get this for free :-)
                                .permitAll() //Allow everyone to see login page. No need to be logged in.
                );



        return http.build();
    }
    //
    // Note:
    //

    // <form action="#" th:action="@{/authenticateTheUser2}" method="POST">
    // loginProcessingUrl("/authenticateTheUser2")

    // They must be same name ==> it can be any name :D it is super
    // You can give ANY values
    // for this configuration.
    // Just stay consistent in your app
}












