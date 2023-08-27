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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    /*
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // Springs Boots looks for tables called users and authorities
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // To use Custom Tables to Authorize and Security
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // Define a query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );
        // Define a query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );
        return jdbcUserDetailsManager;
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

/*
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
    } */
