package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class) //Helper class that contains business rules / validation logic
@Target({ElementType.METHOD, ElementType.FIELD}) //Can apply our annotation to a method or field
@Retention(RetentionPolicy.RUNTIME) //Retain this annotation in the Java class file. How long will the marked annotation be stored or used? Retain this annotation in the bytecode and also use it at runtime by the JVM.
public @interface CourseCode {

    // define default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";


    // define default groups
    public Class<?>[] groups() default {}; //Groups: can group related constraints

    // define default payloads
    public Class<? extends Payload>[] payload() default {}; //Payloads: provide custom details about validation failure (severity level, error code etc)
}












