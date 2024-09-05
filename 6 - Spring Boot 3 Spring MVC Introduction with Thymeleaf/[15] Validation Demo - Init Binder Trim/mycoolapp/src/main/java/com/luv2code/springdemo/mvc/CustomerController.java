package com.luv2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    // this method is called for every web request coming into our request
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        System.out.println("initBinder method is called.");

        // Defined in Spring API
        // true - means trim empty string to null
        // StringTrimmerEditor: removes whitespaces - leading and trailing
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // Register custom editor on the DataBinder
        // For the string class, use this StringTrimmerEditor that i just created
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @GetMapping("/")
    public String showForm(Model theModel) {

        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {

        System.out.println("Last name: |" + theCustomer.getLastName() + "|"); //Use vertical bars to see where white spaces exist

        if (theBindingResult.hasErrors()) {
            return "customer-form";
        }
        else {
            return "customer-confirmation";
        }
    }
}







