package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa;


import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional //Whenever we want to execute queries with JPA, we need to use Transactional Annotation
public class CourseJpaRepository {
    //when you want the jpa to talk to the db, then we use an EntityManager, so we created an entity course and we would be
    //using entityManager to manage our entity

    @PersistenceContext//Express a dependency in a container-managed EntityManager and its associated persistence context
    //Similar to @AutoWired
    private EntityManager entityManager;

    public void insert(Course course){
        entityManager.merge(course); //the merge() is used to merge the state of the given entity into current persistence context
        //all the merge is already done. The merge is already done, the id merges with the id_data, name merges with the name_data,
        //author merges with the author_data and course bean matches with the course table
    }

    public Course findById(long id){
       return entityManager.find(Course.class, id); //the second parameter by primary key which is the id
    }

    public void deleteById(long id){
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }
}
