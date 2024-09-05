package com.luv2code.springboot.cruddemo.config;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)  {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};


        // disable HTTP methods for Employee: PUT, POST, DELETE and PATCH

        /**
         * getExposureConfiguration: Accessing the exposure configuration of the repository
         * forDomainType : Specifying that the configuration applies to the domain type (entity) 'Employee'
         * withItemExposure: Configuring item exposure for the Employee entity. This lambda expression disables the specified HTTP methods for individual items.
         * withCollectionExposure: Configuring collection exposure for the Employee entity. This lambda expression disables the specified HTTP methods for collections of items.
         */
        config.getExposureConfiguration()
                .forDomainType(Employee.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}