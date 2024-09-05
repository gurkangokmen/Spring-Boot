package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    // it match on
    // theAccountDAO.addAccount(myAccount);
    // theAccountDAO.addAccount(myAccount, true);

    // it does not match on
    // theAccountDAO.addAccount(true);
    @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    public void beforeAddAccountAdvice() {

        System.out.println("\n=====>>> Executing @Before advice on method");

    }
}











