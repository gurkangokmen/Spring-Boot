# Spring Boot Revise

## 2 - Spring Boot 3 Inversion of Control and Dependency Injection

## Table of Contents
1. [Inversion of Control (IoC)](#inversion-of-control-ioc)
2. [@SpringBootApplication](#springbootapplication)
3. [Injection Types](#injection-types)
4. [Component Scanning](#component-scanning)
5. [@Qualifier and @Primary](#qualifier-and-primary)
6. [@Lazy](#lazy)
7. [Scope](#scope)
8. [`@PostConstruct` and `@PreDestroy` Bean Life Cycle](#postconstruct-and-predestroy-bean-life-cycle)
9. [Config Bean](#config-bean)

# Inversion of Control (IoC)

```
• The approach of outsourcing the construction and management of objects.
```

```
Create and manage objects (Inversion of Control)
Inject object dependencies (Dependency Injection)
```

```
@Component
@Autowired
```

# @SpringBootApplication

```java
/*
@EnableAutoConfiguration
@ComponentScan
@Configuration
*/
@SpringBootApplication
```

# Injection Types

```
Injection Types - Which one to use?
• Constructor Injection: required dependencies
• Setter Injection: optional dependencies
• Field Injection: Not recommended
```

`Constructor Injection`

```java
public interface Coach {
    String getDailyWorkout();
}


@Component //@Component annotation marks the class as a spring bean
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}


@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;


    // define a constructor for dependency injection
    @Autowired // @Autowired annotation tells to Spring to inject a dependency (Note: If you only have one constructor then @Autowired on constructor is optional)
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

`Setter Injection`

```java
public interface Coach {
    String getDailyWorkout();
}


@Component
@Lazy
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}


@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired //if we write doSomeStuff instead of setCoach, it will be ok. Don't worry :D
    public void setCoach(Coach theCoach) {
        System.out.println("DemoController: inside setCoach() method");
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```

`Field Injection`

```java
public interface Coach {
    String getDailyWorkout();
}

@Component
public class BaseballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}


@Component
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}


@Component
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}


@RestController
public class DemoController {

    // define a private field for the dependency

    // You should use @Primary or @Qualifier, if you have multiple bean.
    @Qualifier("cricketCoach")
    @Autowired //Necessary
    private Coach myCoach;



    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```


# Component Scanning

![Screenshot 2024-11-12 231634](https://github.com/user-attachments/assets/ccb08ded-ff0b-4b51-a6b2-92820cd3df11)


# @Qualifier and @Primary

`Spring will scan @Components, If Multiple Coach Interface Implementations...`

```
Mixing @Primary and @Qualifier
• @Qualifier has higher priority
```

```java
public interface Coach {
    String getDailyWorkout();
}

@Component
public class BaseballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}

@Component
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}

@Component
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}

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
```

```java
public interface Coach {
    String getDailyWorkout();
}

@Component
public class BaseballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}

@Component
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}

@Component
@Primary
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
```


# @Lazy

```
Lazy Initialization

@Lazy
spring.main.lazy-initialization=true

• Lazy initialization feature is disabled by default.

Advantages
• Only create objects as needed
• May help with faster startup time if you have large number of components

Disadvantages
• If you have web related components like @RestController, not created until requested
• May not discover configuration issues until too late
• Need to make sure you have enough memory for all beans once created
```

![Screenshot 2024-11-13 074404](https://github.com/user-attachments/assets/583c24aa-3d09-428a-b21d-8db88cf344aa)

![Screenshot 2024-11-13 074706](https://github.com/user-attachments/assets/17baa2f8-19ba-4b5b-9126-4201e7a213c9)


# Scope

`• Default scope is singleton`

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //SCOPE_PROTOTYPE OR SCOPE_SINGLETON
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```


`@RequestScope` or `@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)`
```java
/*
    @RequestScope
    The request scoped bean is created when an ⭐HTTP request arrives⭐.
    With request scoped bean you will have a bean and you will always get the same instance of the object until this API request sends back the response,
     but when a new request comes, it will send a ⭐new instance⭐.
*/

/*
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    The proxyMode attribute is necessary because at the moment of the instantiation of the web application context, ⭐there is no active request⭐. Spring creates a proxy to be injected as a dependency, and instantiates the target bean when it is needed in a request.

    more information: https://www.baeldung.com/spring-bean-scopes
 */

/*
    @RequestScope
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
 */

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```

`@SessionScope` or `@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)`

```java
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
```


# `@PostConstruct` and `@PreDestroy` Bean Life Cycle


```java
@Component
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    /**
     * Call Order:
     * <br>Constructor
     * <br>PostConstruct (THINK like methods, because doMyStartupStuff is already is a method :D so constructor is first,later bean lifecycle methods)
     * <br>PreDestroy
     */
    // define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }

    // define our destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
```

# Config Bean

```java
public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}

@Configuration // It indicates that a class contains bean definitions and configuration instructions for a Spring application.
public class SportConfig {
    /*
      it is initialized. so it isn't lazy! ✔️
    */
    @Bean //The bean id defaults to the method name
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired // Inject the bean using the bean id
    public DemoController(@Qualifier("swimCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

}
```


```java
public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}

@Configuration
public class SportConfig {
    /*
      it is initialized. so it isn't lazy! ✔️
    */
    @Bean("aquatic") //The bean id defaults to the method name
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach) { //Our custom bean id
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

}
```