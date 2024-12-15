# Spring Boot Revise

## ❤️‍🔥PDF-10 
## Aspect-Oriented Programming (AOP)

## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛


### We need to add `Logging and Security` to our code

### But There is a `PROBLEM!`

### We cannot write logging and security codes every dao, service and etc...

### It create problems...

### `Code Tangling`
### `Code Scattering` (If we need to change logging or security code, We have to update ALL classes)

### `Other possible solutions?`

### `Inheritance?` → no multiple inheritance
### `Delegation?` → Still would need to update classes if we wanted to add new feature

## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
## THE BEST SOLUTION `Aspect-Oriented Programming (AOP)`
```
Aspects
• Aspect can be reused at multiple locations
```
```
Benefits of AOP
• Code for Aspect is defined in a single class
• Business code in your application is cleaner
• Configurable
```
```
AOP Use Cases
• Most common: logging, security, transactions
• Audit logging: who, what, when, where
• Exception handling: log exception and notify DevOps team via SMS/email
• API Management: 1-)how many times has a method been called user? 
	              2-)analytics: what are peak times? what is average load? who is top user?
```
```
AOP: Advantages and Disadvantages

Advantages                   Disadvantages
• Reusable modules           • Too many aspects and app flow is hard to follow
• Resolve code tangling      • Minor performance cost for aspect execution (run-time weaving)
• Resolve code scatter
• Applied selectively based
on configuration
```

```
AOP Terminology
• Aspect: module of code for a cross-cutting concern (logging, security, …)
• Advice: What action is taken and when it should be applied
• Join Point: When to apply code during program execution
• Pointcut: A predicate expression for where advice should be applied
```
```
Advice Types
• Before advice: run before the method
• After finally advice: run after the method (finally)
• After returning advice: run after the method (success execution)
• After throwing advice: run after method (if exception thrown)
• Around advice: run before and after method
```
## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
## Dependency
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

`Spring Boot will automatically enable support for AOP`

`No need to explicitly use @EnableAspectJAutoProxy … we get it for free`

## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
## Pointcut Expression Language
![Screenshot 2024-07-06 155252](https://github.com/gurkangokmen/algorithms/assets/122023578/be0788fd-dd5c-4af4-b323-d05a412e29b9)
![Screenshot 2024-07-06 155626](https://github.com/gurkangokmen/algorithms/assets/122023578/ebedd0f9-151f-43e5-922d-c094f3b7ee62)


## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
## @Before Advice 
```
Advice Types
• [@Before] Before advice: run before the method
• After finally advice: run after the method (finally)
• After returning advice: run after the method (success execution)
• After throwing advice: run after method (if exception thrown)
• Around advice: run before and after method
```

```
@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    // Run this code BEFORE - target object method: "public void addAccount()"
    @Before("execution(public void addAccount())") //pointcut expression
    public void beforeAddAccountAdvice() { // Can be any method name

        // Add our custom code
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

    }
}
```

## @AfterReturning Advice
```
Advice Types
• Before advice: run before the method
• After finally advice: run after the method (finally)
• [@AfterReturning] After returning advice: run after the method (success execution)
• After throwing advice: run after method (if exception thrown)
• Around advice: run before and after method
```
```
@AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

    }
```
### Modify Return Value

```
@AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

        //
        // @AfterReturning Advice - Modify Return Value
        //

        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts

        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }
```

## @AfterThrowing Advice
```
Advice Types
• Before advice: run before the method
• After finally advice: run after the method (finally)
• After returning advice: run after the method (success execution)
• [@AfterThrowing] After throwing advice: run after method (if exception thrown)
• Around advice: run before and after method
```
```
@AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);
    }

```
```
=====>>> Executing @AfterThrowing on method: AccountDAOImpl.findAccounts(..)

=====>>> The exception is: java.lang.RuntimeException: No soup for you!!!


Main Program: ... caught exception: java.lang.RuntimeException: No soup for you!!!


Main Program: demoTheAfterThrowingAdvice
----
null
```


`If you use try-catch, @AfterThrowing does not work because already handle exception`
```
@Override
public List<Account> findAccounts(boolean tripWire) {

    // for academic purposes ... simulate an exception
    if (tripWire) {
        throw new RuntimeException("No soup for you!!!");
    }

    List<Account> myAccounts = new ArrayList<>();

    // create sample accounts
    Account temp1 = new Account("John", "Silver");
    Account temp2 = new Account("Madhu", "Platinum");
    Account temp3 = new Account("Luca", "Gold");

    // add them to our accounts list
    myAccounts.add(temp1);
    myAccounts.add(temp2);
    myAccounts.add(temp3);

    return myAccounts;
}
```

### Access the Exception
```
@AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);
    }
```

## @After Advice

```
Advice Types
• Before advice: run before the method
• [@After] After finally advice: run after the method (finally)
• After returning advice: run after the method (success execution)
• After throwing advice: run after method (if exception thrown)
• Around advice: run before and after method
```
```
// @After will run for success or failure (finally)
@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

	// print out which method we are advising on
	String method = theJoinPoint.getSignature().toShortString();
	System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
}
```

`The @After advice does not have access to the exception`

`The @After advice does not have access return value`

## @Around Advice
```
Advice Types
• Before advice: run before the method
• After finally advice: run after the method (finally)
• After returning advice: run after the method (success execution)
• After throwing advice: run after method (if exception thrown)
• [@Around] Around advice: run before and after method
```
```
@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// BEFORE OPERATIONS
        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();


        // theProceedingJoinPoint ====>  Handle to target method
        //        .proceed() ====> Execute the target method

        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

		// AFTER OPERATIONS
        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }
```

`You can handle / swallow /stop the exception `
### Handle Exception
```
@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
public Object aroundGetFortune(
        ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
    // BEFORE OPERATIONS
    // print out method we are advising on
    String method = theProceedingJoinPoint.getSignature().toShortString();
    System.out.println("\n=====>>> Executing @Around on method: " + method);

    // get begin timestamp
    long begin = System.currentTimeMillis();

    // now, let's execute the method
    Object result = null;

    try {
        result = theProceedingJoinPoint.proceed();
        // AFTER OPERATIONS
    }

    //
    // We are handling the exception here in our advice
    //

    catch (Exception exc) {
        // log the exception
        System.out.println(exc.getMessage());

        // give user a custom message
        result = "Major accident! But no worries, your private AOP helicopter is on the way!";
    }

    // get end timestamp
    long end = System.currentTimeMillis();

    // compute duration and display it
    long duration = end - begin;
    System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

    return result;
}
```
### Rethrow Exception
```
@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
public Object aroundGetFortune(
        ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

    // BEFORE OPERATIONS

    // print out method we are advising on
    String method = theProceedingJoinPoint.getSignature().toShortString();
    System.out.println("\n=====>>> Executing @Around on method: " + method);

    // get begin timestamp
    long begin = System.currentTimeMillis();

    // now, let's execute the method
    Object result = null;

    try {
        result = theProceedingJoinPoint.proceed();
        // AFTER OPERATIONS
    }
    catch (Exception exc) {
        // log the exception
        System.out.println(exc.getMessage());

        // rethrow exception
        throw exc;
    }

    // get end timestamp
    long end = System.currentTimeMillis();

    // compute duration and display it
    long duration = end - begin;
    System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

    return result;
}
```

## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
## Problem 'Reuse Advice'
`How can we reuse a pointcut expression?`
## Solution
```
@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice


    // Step 1 : Create Pointcut Declaration
    // NO PARAMS, NO BODY
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {} // Name of pointcut declaration, Can have any name


    // Step 2 : Apply pointcut declaration to advices
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {

        System.out.println("\n=====>>> Executing @Before advice on method");

    }
}
```

## Problem 'Combine Advices'
`How to apply multiple pointcut expressions to single advice?`
## Solution
![Screenshot 2024-07-06 164118](https://github.com/gurkangokmen/algorithms/assets/122023578/52e3b7ac-ef28-4505-8e83-0b999b6c886f)
![Screenshot 2024-07-06 164126](https://github.com/gurkangokmen/algorithms/assets/122023578/ff955922-58b2-466b-b21a-8e27306142d0)

```
@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))") // Match getter methods
    private void getter() {} // Method can have any name

    // create a pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))") // Match setter methods
    private void setter() {} // Method can have any name

    //
    // Combine pointcuts
    //

    // create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {} // Method can have any name

    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics");
    }
}

```


## Problem 'Order of Advices'
`How to control the order of advices being applied?`
## Solution
```
@Order annotation

1️⃣ Lower numbers have higher precedence
• Range: Integer.MIN_VALUE to Integer.MAX_VALUE
• Negative numbers are allowed
• Does not have to be consecutive
```

![Screenshot 2024-07-06 164924](https://github.com/gurkangokmen/algorithms/assets/122023578/e009b0e1-e34d-4f46-9d05-bbee68a1066c)
![Screenshot 2024-07-06 165218](https://github.com/gurkangokmen/algorithms/assets/122023578/0d91e84f-f0c6-4a91-b89f-4af5745f8e93)


## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛

## Reading Method Arguments with JoinPoints
```
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        // Step 1: display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // Step 2: display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }

}
```
## 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
