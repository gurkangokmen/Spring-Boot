package com.luv2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model theModel) { //Model allows us to share information between Controllers and view pages (Thymeleaf)

        theModel.addAttribute("customer", new Customer()); //name - value

        return "customer-form"; //logical name of view page will map to: customer-form.html
    }

    //<form th:action="@{/processForm}" th:object="${customer}" method="POST">  ==>  @PostMapping("/processForm") must be same with th:action="@{/processForm}"
    @PostMapping("/processForm")
    public String processForm(
            //@Valid: Tell Spring MVC to perform validation
            //Validation rules:  @NotNull(message="is required") @Size(min=1, message="is required")
            //@ModelAttribute("customer"): Model attribute name
            //<form th:action="@{/processForm}" th:object="${customer}" method="POST">  ==>  @ModelAttribute("customer") must be same with th:object="${customer}
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) { //The results of validation

        System.out.println("Last name: |" + theCustomer.getLastName() + "|"); //Use vertical bars to see where white spaces exist

        if (theBindingResult.hasErrors()) {
            return "customer-form";
        }
        else {
            return "customer-confirmation";
        }
    }
}







