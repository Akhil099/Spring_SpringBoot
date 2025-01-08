package com.in28minutes.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping(path="/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    //It is going to the UserSeviceDao that is Data access object and from the usersdaoservice we are retruning all the users


    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveById(@PathVariable int id){

        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id: "+id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @GetMapping(path="/users/by-name/{name}")
    public ResponseEntity<Object> retrieveByName(@PathVariable String name){
        Predicate<? super User> predicate = user -> user.getName().equalsIgnoreCase(name) ;
        Optional<User> userOptional = service.findAll().stream().filter(predicate).findFirst();
        if(userOptional.isPresent())
            return ResponseEntity.ok(userOptional.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping(path="/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
        //return ResponseEntity.created(null).build();
    }

    @DeleteMapping(path = "/users/delete/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteUser(id);
    }
}
