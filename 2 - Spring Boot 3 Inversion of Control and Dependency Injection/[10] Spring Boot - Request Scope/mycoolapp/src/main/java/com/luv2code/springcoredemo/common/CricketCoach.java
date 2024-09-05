package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

/*
    @RequestScope
    The request scoped bean is created when an ⭐HTTP request arrives⭐.
    With request scoped bean you will have a bean and you will always get the same instance of the object until this API request sends back the response,
     but when a new request comes, it will send a ⭐new instance⭐.
*/

/*
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    The proxyMode attribute is necessary because at the moment of the instantiation of the web application context, ⭐there is no active request⭐. Spring creates a proxy to be injected as a dependency, and instantiates the target bean when it is needed in a request.

    more information: https://www.baeldung.com/spring-bean-scopes
 */

/*
    @RequestScope
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
 */

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
