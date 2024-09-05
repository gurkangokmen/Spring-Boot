package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository //for component scanning
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void addAccount() {

        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");

    }
}
