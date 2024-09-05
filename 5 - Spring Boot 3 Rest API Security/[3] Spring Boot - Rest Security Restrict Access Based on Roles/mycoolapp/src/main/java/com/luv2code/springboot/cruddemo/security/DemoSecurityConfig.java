package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

        //
        // CSRF
        //

        // ⭐Spring Security'de CSRF Otomatik Olarak Açıktır.

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH

        // It is used to disable CSRF (Cross-Site Request Forgery) protection on requests coming over HTTP.

        // ⭐CSRF (Cross Site Request Forgery) yani Türkçe karşılığı ile Siteler Arası İstek Sahtekarlığı,
        // herhangi bir web uygulamasında oturum açmış bir kullanıcının
        // oturumunu kullanarak kullanıcının istekleri dışında işlemler yapılmasıdır.
        // Uygulamaya giden isteklerin hangi kaynaktan ve nasıl gönderildiğinin kontrol edilmeyen sistemlerde bu zafiyet meydana gelir.

        //
        // Relationship between "Stateless REST APIs" and "CSRF"
        //

        // Stateless REST APIs: REST (Representational State Transfer) is an architectural style
        // for designing networked applications. RESTful APIs are often designed to be stateless,
        // meaning that each HTTP request from a client to a server must contain all the information
        // necessary to understand and process the request. In a stateless API,
        // the server does not store any client-specific session data between requests.
        // Each request should be independent and self-contained.

        // In a stateless API, each request should contain all the necessary
        // authentication information (e.g., tokens, API keys) as part of the request itself.
        // Since there are no server-side sessions, it becomes more challenging for attackers
        // to forge requests because they would need to know the specific authentication
        // information for each request.

        // CSRF attacks often rely on the fact that a user is already authenticated and
        // has an active session on a vulnerable website. Stateless APIs don't maintain
        // user sessions in the same way, making it more difficult for attackers to
        // exploit CSRF vulnerabilities.

        // RESTful APIs often use HTTP methods like POST, PUT, DELETE, and PATCH
        // for modifying resources.
        // These methods typically require specific actions by the client,
        // and attackers have a harder time forging malicious requests
        // without knowledge of the required action and authentication.

        //
        // Summary
        //

        // In general, CSRF is not required for
        // stateless REST APIs that use
        // POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}













