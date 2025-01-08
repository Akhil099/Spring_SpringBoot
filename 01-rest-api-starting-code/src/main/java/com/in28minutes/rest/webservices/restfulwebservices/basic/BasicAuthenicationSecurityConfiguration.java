package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

//@Configuration
public class BasicAuthenicationSecurityConfiguration {

    //httpSecurity can support chaining in code
    //filter chain

    //Todos are:
    //i)BAsic Filter chain
    // 1)authenticate all requests
    // 2)disabling csrf
    //3) stateless rest api

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //1. We need to make sure that the response to Http.Options call will not go through the access control i.e provide access to Preflight  request
        //2. We need send a basic authentiaction request in Hello-World url
        http
            .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf()
            .disable();
        return http.build();
    }
}
