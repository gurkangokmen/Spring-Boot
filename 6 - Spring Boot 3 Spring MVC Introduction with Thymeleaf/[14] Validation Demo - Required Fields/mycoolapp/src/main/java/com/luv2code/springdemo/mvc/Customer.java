package com.luv2code.springdemo.mvc;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {

    private String firstName;

    //Validation Rules:

    //
    // @NotNull
    //

    //  It warns us ===> not-null fields must be initialized
    //  It is only a "warning" and not an "error". You can safely ignore this.
    //  This will not impact Spring MVC validation in your application.
    // private String lastName = "";

    // UPDATE: 3/16/2024 - I do not get any warns no longer about not-null fields must be initialized!

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
