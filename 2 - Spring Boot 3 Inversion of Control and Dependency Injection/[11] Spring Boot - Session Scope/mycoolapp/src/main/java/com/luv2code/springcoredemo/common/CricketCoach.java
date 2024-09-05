package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/*
    @SessionScope
    The request scoped bean is created when an ⭐HTTP request arrives⭐.
    When we use session scoped bean, it always returns the ⭐same instance⭐ of the object for the entire session (user-level session).
    However, when the user logs out, you will get a ⭐new instance⭐ of the object for a new user session

    Basicly try normal window and incognito window!
    They have different instance :D
*/


/*
    @SessionScope
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
 */


@Component
@SessionScope
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
