package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RequestMapping annotation also works in here :D ==> Test results reached succes!

//



// @RestController → returns "helloworld" plain text!
// @Controller → returns "helloworld" html page!

@Controller
public class DemoController {

    // create a mapping for "/hello"

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        // We have Thymeleaf dependency in Maven POM
        // Spring Boot will auto-configure to use Thymeleaf
        return "helloworld"; //src/main/resources/templates/helloworld.html
    }
}
