package com.in28minutes.springboot.learn_jpa_and_hibernate.course.springdatajpa;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthor(String author);  //This is a naming convention, where you would be querying based on findBy"Column_name"

    List<Course> findByName(String name);
}
