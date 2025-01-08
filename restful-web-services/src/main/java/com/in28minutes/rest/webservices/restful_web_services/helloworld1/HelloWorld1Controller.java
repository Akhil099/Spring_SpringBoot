package com.in28minutes.rest.webservices.restful_web_services.helloworld1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//We can expose the REST API
public class HelloWorld1Controller {

//    private MessageSource messageSource;
//
//    public HelloWorld1Controller(MessageSource messageSource){
//        this.messageSource = messageSource;
//    }

    @GetMapping(path = "/hello-world1")
    public String helloWorld(){
        return "Hello World V2";
    }
    @GetMapping(path = "/hello-world1-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Now!");  //Here we are returning a java bean body
    }

    @GetMapping(path = "/hello-world1/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World Now! %s", name));  //Here we are returning a java bean body
    }

//    @GetMapping(path = "/hello-world-internationalized")
//    public String helloWorldInternationalized(){
//        Locale locale = LocaleContextHolder.getLocale();
//        return messageSource.getMessage("good.morning.message", null, "Default Mesasage", locale);
////        return "Hello World V2";
//    }
}
