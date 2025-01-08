package com.in28minutes.springboot.learnspringboot;


//return course details with CourseId, name and author like this in json format
//[
//        {
//            "id" : 1,
//            "name:" "LearnAWS",
//            "author" : "in28minutes"
//        }
//        ]


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class CurrencyConfigurationController {

    //We need to map url to mappings

    @Autowired
    private CurrencyServiceConfiguration configuration;
    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses(){
        return configuration;
    }
}
