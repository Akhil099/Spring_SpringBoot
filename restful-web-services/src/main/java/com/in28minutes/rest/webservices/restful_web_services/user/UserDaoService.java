package com.in28minutes.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    //Usually will be going to use JPA/Hibernate as a database but
    //In this example using a UserDaoService as a class which is having a static list

    private static List<User> users = new ArrayList<>();

    private static int count = 0;
    static{
        users.add(new User(++count, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++count, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++count, "Jim", LocalDate.now().minusYears(30)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId()==id;
//        ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id").buildAndExpand()
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User addUser(User user){
        user.setId(++count);
        users.add(user);
        return user;
    }

    public void deleteUser(int id){
        Predicate<?super User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }
}
