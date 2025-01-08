package com.in28minutes.rest.webservices.restful_web_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication{

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}


	//We need to override the method for Cross Origin Request here by extending our Main class with the WebMvcCongigurer class and
	// overriding the method addCorsMapping()

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") //Allows/enables cross-origin request handling for the specified path pattern. Eg is exact path mapping URIs(such as "/admin") are supported as well as ANt-style path patterns such as "/admin/**"
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000");
			}
		};
	}


}
