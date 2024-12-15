# Spring Boot Revise

## 5 - Spring Boot 3 Rest API Security

## Table of Contents

1. [Dependencies](#dependencies)
2. [Username and Password](#username-and-password)
3. [Configurations](#configurations)
4. [Annotations](#annotations)
5. [InMemoryUserDetailsManager](#inmemoryuserdetailsmanager)
6. [Security Role](#security-role)
7. [JDBC Plain Text Password](#jdbc-plain-text-password)
8. [JDBC Bcrypt Text Password](#jdbc-bcrypt-text-password)
9. [JDBC Bcrypt Text Password - Custom Table](#jdbc-bcrypt-text-password---custom-table)

# Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

# Username and Password

```
default username: user

password: <generated different password every run>
```

# Configurations

```
spring.security.user.name=scott
spring.security.user.password=test123
```

# Annotations
```java
@Configuration
@Bean
```

# InMemoryUserDetailsManager

```java
// DemoSecurityConfig dominates application.properties security configs (application.properties security configs is invalid)
@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}
```

# Security Role

`SpringBootWebSecurityConfiguration`

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
    );

    // use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaults());

    // In general, CSRF is not required for
    // stateless REST APIs that use
    // POST, PUT, DELETE and/or PATCH
    http.csrf(csrf -> csrf.disable());

    return http.build();
}
```


# JDBC Plain Text Password

```java
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {

    // Tell Spring Security to use
    // JDBC authentication
    // with our data source

    // No longer
    // hard-coding users :-)
    return new JdbcUserDetailsManager(dataSource);
}
```

`Default Spring Security Database Schema`

```
users
---------------------
username VARCHAR(50)
password VARCHAR(50)
enabled TINYINT(1)


authorities
---------------------
username VARCHAR(50)
authority VARCHAR(50)
```


`Password Style`
```
{noop}test123
```

`Role Style`
```
ROLE_EMPLOYEE
```

# JDBC Bcrypt Text Password

`Password Style`
```
{bcrypt}test123
```

# JDBC Bcrypt Text Password - Custom Table

```java
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {

    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // define query to retrieve a user by username
    jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id, pw, active from members where user_id=?"); //Question mark “?” Parameter value will be the user name from login

    // define query to retrieve the authorities/roles by username
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id, role from roles where user_id=?"); //Question mark “?” Parameter value will be the user name from login

    return jdbcUserDetailsManager;
}
```