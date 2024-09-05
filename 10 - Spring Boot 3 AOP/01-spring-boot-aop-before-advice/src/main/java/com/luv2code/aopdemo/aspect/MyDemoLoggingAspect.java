package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect; //spring-boot-starter-aop
import org.aspectj.lang.annotation.Before; //spring-boot-starter-aop
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    // Run this code BEFORE - target object method: "public void addAccount()"
    @Before("execution(public void addAccount())") //pointcut expression
    public void beforeAddAccountAdvice() { // Can be any method name

        // Add our custom code
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

    }
}











