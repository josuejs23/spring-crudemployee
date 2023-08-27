package com.josuejs23.springboot.crudemployee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles(Roles.EMPLOYEE.getRole())
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles(Roles.EMPLOYEE.getRole(),Roles.MANAGER.getRole())
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles(Roles.EMPLOYEE.getRole(),Roles.MANAGER.getRole(),Roles.ADMIN.getRole())
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests( configurer ->
           configurer
                   .requestMatchers(HttpMethod.GET, "/api/members").hasRole(Roles.EMPLOYEE.getRole())
                   .requestMatchers(HttpMethod.GET, "/api/members/**").hasRole(Roles.EMPLOYEE.getRole())
                   .requestMatchers(HttpMethod.POST, "/api/members").hasRole(Roles.MANAGER.getRole())
                   .requestMatchers(HttpMethod.PUT, "/api/members/**").hasRole(Roles.MANAGER.getRole())
                   .requestMatchers(HttpMethod.DELETE, "/api/members/**").hasRole(Roles.ADMIN.getRole())

        );

        // use Http Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery(CSRF)
        // in general, not required for stateless REST apis that use POST, GET PUT, DELETE, PATCH
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
