package com.in28minutes.springboot.learnspringboot;


//return course details with CourseId, name and author like this in json format
//[
//        {
//            "id" : 1,
//            "name:" "LearnAWS",
//            "author" : "in28minutes"
//        }
//        ]


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class CourseController {

    //We need to map url to mappings

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1, "Learn AWS", "in28minutes"),
                new Course(2, "Devops", "in28minutes"),
                new Course(3, "Learn Azure", "in28minutes"),
                new Course(4, "Learn GCP", "in28minutes")

        );
    }
}
