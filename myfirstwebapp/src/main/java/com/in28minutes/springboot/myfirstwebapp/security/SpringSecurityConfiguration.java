package com.in28minutes.springboot.myfirstwebapp.security;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //We would be directly creating beans in this class and that is why we are naming it as @Configuration and the beans will also be returned

    //Typically to store usernames and passwords we use LDAP or atleast a database
    //In this we would InMemory configure

//    InMemoryUserDetailsManager
//    InMemoryUserDetailsManager(UserDetails... users)

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){

        UserDetails userDetails1 = createNewUser("akhil", "UmassBoston2024");
        UserDetails userDetails2 = createNewUser("in28minutes", "dummy");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder =  input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
                .roles("USER","ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //All URLs are protected
    //a login form is shown for unauthorised requests
    //CSRF disable
    //frames

    // We are overiding the filterChain so we need to define/override all the 4 features or define the entire chain again
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()); // Making sure that all URLs are protected
        http.formLogin(Customizer.withDefaults()); //Making sure that the login page is shown as soon as there is an unauthorized request

        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//                .ignoringRequestMatchers(
//                "/h2-console/**", "/api/public/**"));
        http.headers(headers ->headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        return http.build();
    }

}
