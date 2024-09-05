package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency

    private Coach myCoach;


    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        myCoach = theCoach;
    }
    // The reason it is acceptable to use "cricketCoach" instead of "CricketCoach"
    // in the '@Qualifier' annotation is because Spring allows for case-insensitive matching of bean names.
    // Spring will still correctly resolve the dependency based on case-insensitive matching.

    // cricketcoach =====> ERROR
    // CricketCoach =====> ERROR

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}






