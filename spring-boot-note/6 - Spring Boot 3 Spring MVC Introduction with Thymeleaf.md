# Spring Boot Revise

## 6 - Spring Boot 3 Spring MVC Introduction with Thymeleaf

## Table of Contents

1. [Dependencies](#dependencies)
2. [Basic Thymeleaf](#basic-thymeleaf)
3. [Basic Thymeleaf CSS](#basic-thymeleaf-css)
4. [Form](#form)
5. [Read Request Parameter and Change It](#read-request-parameter-and-change-it)
6. [Binding Request Parameter](#binding-request-parameter)
7. [GET Mapping and POST Mapping](#get-mapping-and-post-mapping)
8. [Form Data Binding TextFields](#form-data-binding-textfields)
9. [Form Data Binding Dropdown List Basics](#form-data-binding-dropdown-list-basics)
10. [Form Data Binding Radio Buttons Basic](#form-data-binding-radio-buttons-basic)
11. [Form Data Binding Radio Buttons Read From Props File](#form-data-binding-radio-buttons-read-from-props-file)
12. [Form Data Binding Checkboxes Basic](#form-data-binding-checkboxes-basic)
13. [Form Data Binding Checkboxes Read From Props File](#form-data-binding-checkboxes-read-from-props-file)
14. [Validation Demo - Required Fields](#validation-demo---required-fields)
15. [Validation Demo - Init Binder Trim](#validation-demo---init-binder-trim)
15. [Validation Demo - Number Ranges](#validation-demo---number-ranges)
15. [Validation Demo - Regular Expressions](#validation-demo---regular-expressions)
15. [Validation Demo - Make Integer Fields Required](#validation-demo---make-integer-fields-required)
15. [Validation Demo - Handle Strings for Integer Fields](#validation-demo---handle-strings-for-integer-fields)
15. [Validation Demo - Custom Validation Rule](#validation-demo---custom-validation-rule)


# Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

# Basic Thymeleaf

```java
@Controller
public class DemoController {

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        return "helloworld"; //src/main/resources/templates/helloworld.html
    }
}
```

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Thymeleaf Demo</title>
</head>

<body>

    <p th:text="'Time on the server is ' + ${theDate}" />

</body>

</html>
```

# Basic Thymeleaf CSS

```java
@Controller
public class DemoController {

    // create a mapping for "/hello"

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }
}
```


```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Thymeleaf Demo</title>

    <!-- reference CSS file -->
    <link rel="stylesheet" th:href="@{/css/demo.css}" />
</head>

<body>

    <p th:text="'Time on the server is ' + ${theDate}" class="funny" />

</body>

</html>
```

# Form

```java
@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form

    @RequestMapping("/showForm")
    public String showForm() {

        // src/main/resources/templates/helloworld-form.html
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }
}
```

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Hello World - Input Form</title>
</head>
<body>

  <form th:action="@{/processForm}" method="GET">

      <input type="text" name="studentName"
             placeholder="What's your name?" />

      <input type="submit" />

  </form>

</body>
</html>
```

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Thymeleaf Demo</title>
</head>

<body>

Hello World of Spring!

<br><br>

Student name: <span th:text="${param.studentName}" />

</body>

</html>
```

# Read Request Parameter and Change It

```java
@RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result); //model: name | value

        return "helloworld";
    }
```

# Binding Request Parameter

```java
@RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model model) {

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey My Friend from v3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
```

# GET Mapping and POST Mapping

```java
@GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

@PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model model) {

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey My Friend from v3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
```

# Form Data Binding TextFields

```java
@GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        return "student-form";
    }
```

```html
<form th:action="@{/processStudentForm}" th:object="${student}" method="POST">

    <!--    *{...} is shortcut syntax for: ${student.firstName}  -->
    First name: <input type="text" th:field="*{firstName}" />

    <br><br>

    Last name: <input type="text" th:field="*{lastName}" />

    <br><br>

    <input type="submit" value="Submit" />

</form>
```

# Form Data Binding Dropdown List Basics

```html
<select th:field="*{country}">

       <option th:value="Brazil">Brazil</option>
        <option th:value="France">France</option>
        <option th:value="Germany">Germany</option>
        <option th:value="India">India</option>

    </select>
```

# Form Data Binding Dropdown List Read From Props File

```java
@Value("${countries}")
    private List<String> countries;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        // add the list of countries to the model
        theModel.addAttribute("countries", countries);

        return "student-form";
    }
```

```html
<select th:field="*{country}">

        <!--        loop over the list of countries        -->
        <!--        "tempCountry" is the temp loop variable         -->
        <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />

    </select>
```

# Form Data Binding Radio Buttons Basic

```html
<input type="radio" th:field="*{favoriteLanguage}" th:value="Go">Go</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Java">Java</input>
<input type="radio" th:field="*{favoriteLanguage}" th:value="Python">Python</input>
```


# Form Data Binding Radio Buttons Read From Props File

```java
@Value("${languages}")
private List<String> languages;

@GetMapping("/showStudentForm")
public String showForm(Model theModel) {

    // create a student object
    Student theStudent = new Student();

    // add student object to the model
    theModel.addAttribute("student", theStudent);

    // add the list of countries to the model
    theModel.addAttribute("countries", countries);

    // add the list of languages to the model
    theModel.addAttribute("languages", languages);

    return "student-form";
}
```

```html
Favorite Programming Language:
<!-- th:field="*{favoriteLanguage}" Binding to property on Student object-->
<!-- th:each="tempLang : ${languages}" loop over the list of languages ===> tempLang is the temp loop variable-->
<!-- th:value="${tempLang}"Value sent during form submission-->
<!-- th:text="${tempLang}" Displayed to user-->
<input type="radio" th:field="*{favoriteLanguage}"
                    th:each="tempLang : ${languages}"
                    th:value="${tempLang}"
                    th:text="${tempLang}" />
```

# Form Data Binding Checkboxes Basic

```html
Favorite Operating Systems:
<!--    If a value has spaces, place it in single-quotes    -->
<input type="checkbox" th:field="*{favoriteSystems}" th:value="Linux">Linux</input>
<input type="checkbox" th:field="*{favoriteSystems}" th:value="macOS">macOS</input>
<input type="checkbox" th:field="*{favoriteSystems}" th:value="'Microsoft Windows'">Microsoft Windows</input>
```

# Form Data Binding Checkboxes Read From Props File

```java
@Value("${systems}")
private List<String> systems;

@GetMapping("/showStudentForm")
public String showForm(Model theModel) {

    // create a student object
    Student theStudent = new Student();

    // add student object to the model
    theModel.addAttribute("student", theStudent);

    // add the list of countries to the model
    theModel.addAttribute("countries", countries);

    // add the list of languages to the model
    theModel.addAttribute("languages", languages);

    // add the list of systems to the model
    theModel.addAttribute("systems", systems);

    return "student-form";
}
```

```html
Favorite Operating Systems:

<input type="checkbox" th:field="*{favoriteSystems}"
                        th:each="tempSystem : ${systems}"
                        th:value="${tempSystem}"
                        th:text="${tempSystem}" />
```


# Validation Demo - Required Fields

```java
public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName;

    ...
}
```


```java
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
```


```html
<form th:action="@{/processForm}" th:object="${customer}" method="POST">

    First name: <input type="text" th:field="*{firstName}" />

    <br><br>

    Last name (*): <input type="text" th:field="*{lastName}" />

    <!-- Add error message (if present) -->
    <span th:if="${#fields.hasErrors('lastName')}"
          th:errors="*{lastName}"
          class="error"></span>

    <br><br>

    <input type="submit" value="Submit" />

</form>
```
# Validation Demo - Init Binder Trim

```java
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

    ...

```

# Validation Demo - Number Ranges

```java
public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName = "";

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;

    ...
}
```

# Validation Demo - Regular Expressions

```java
public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName = "";

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    ...
}
```

# Validation Demo - Make Integer Fields Required

```java
public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName = "";


    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer  freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    ...

}
```

# Validation Demo - Handle Strings for Integer Fields

`messages.properties`
```
typeMismatch.customer.freePasses=Invalid number
```


# Validation Demo - Custom Validation Rule

```java
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
```

```java
public class CourseCodeConstraintValidator  implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value(); //Assign the value passed in our annotation
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
        //String theCode = HTML Form Data entered by the user
        //ConstraintValidatorContext theConstraintValidatorContext = You can place additional error messages here

        //Validation logic: Test if the form data starts with our course prefix ==> does it start with "LUV"?
        boolean result;

        if (theCode != null) {
            result = theCode.startsWith(coursePrefix);
        }
        else {
            result = true;
        }

        return result;
    }
}
```