package com.in28minutes.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {

    public static void main(String[] args) {
        //1: Launch a Spring Context
        //if there is any exception that occurs in the code then we can use try with resources block
        try (var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            //Spring Container class used is ApplicationContext class
            //2: Configure the things we want Spring to manage  - using @Configuration class
            //by creating a Configuration class which is defined using Annotation @C onfiguration class eg, HelloWorldConfiguration class
            //name:@bean


            //3:Retrieves the bean managed  by Spring, we get the retrieve the beans by context.getBean(name of the bean)
            System.out.println(context.getBean("name"));//method name given in getBean()
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("person"));
            System.out.println(context.getBean("person2MethodCall"));
            System.out.println(context.getBean("person3Parameters"));
            System.out.println(context.getBean("person4Qualifier"));
            System.out.println(context.getBean(Address.class));  //Once Spring has started managing the beans, then we can either
            //give the name of the bean or we can use the type of the bean to retrieve the data

            // System.out.println(context.getBean(Address.class));

            //Check the number of beans managed by Spring
            //first we are converting names into Stream and then we are applying the for each loop
            //This is used to get all the bean names of all the spring context
            Arrays.stream(context.getBeanDefinitionNames()).forEach((name) -> System.out.println(name));
        }
    }
}
