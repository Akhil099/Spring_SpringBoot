package com.in28minutes.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


record Person(String name, int age, Address address){};

record Address(String firstLine, String city){}; //when we create a java class we typically need to create a lot of getter, setter
//equals, toString, constructor, hashcode, so inorder to eliminate such verbosity we are using the keyword record

@Configuration
public class HelloWorldConfiguration {
    @Bean  //Things which are managed by Spring are called as bean
    public String name(){
        return "Akhil";
    }

    @Bean
    public int age(){
        return 10;
    }

    @Bean
    public Person person(){
        var person = new Person("Nag", 25, new Address("3 Oyster Bay Rd, Apt 33", "Boston"));
        return person;
    }

    @Bean
    public Person person2MethodCall(){
        var person = new Person(name(), age(), ad1());  //if we want to call or pass the values into the beans from the values of other beans, then
        // we can do this in 2 ways, one by methodCall or
        return person;
    }

    @Bean
    public Person person3Parameters(String name, int age, Address address2){
        var person = new Person(name, age, ad1());  //if we want to call or pass the values into the beans from the values of other beans, then
        // we can do this in 2 ways, one by methodCall or
        return person;
    }

    @Bean
    public Person person4Qualifier(String name, int age, @Qualifier("address3Qualifier") Address address2){
        var person = new Person(name, age, address2);  //if we want to call or pass the values into the beans from the values of other beans, then
        // we can do this in 2 ways, one by methodCall or
        return person;
    }

    @Bean(name = "ad2") //Customize beans name using custom names
    @Primary
    public Address ad1(){  //ad1 is the method name
        var addss = new Address("77 BeachPoint Pl, Dorchester", "Boston");
        return addss;
    }

    @Bean//Customize beans name using custom names
    @Qualifier("address3Qualifier")
    public Address ad3(){  //ad1 is the method name
        var addssq = new Address("Malkajgiri", "Hyderabad");
        return addssq;
    }
}
