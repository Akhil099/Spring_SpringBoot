package com.in28minutes.rest.webservices.restful_web_services.user;

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

//    private UserDaoService service;
    private UserRepository userRepository;

    private PostRepository postRepository;

//    public UserJpaResource(UserDaoService service){
//        this.service = service;
//    }
    public UserJpaResource(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path="/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }
    //It is going to the UserSeviceDao that is Data access object and from the usersdaoservice we are retruning all the users


    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveById(@PathVariable int id){

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id: "+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @GetMapping(path="/jpa/users/by-name/{name}")
    public ResponseEntity<Object> retrieveByName(@PathVariable String name){
        Predicate<? super User> predicate = user -> user.getName().equalsIgnoreCase(name) ;
        Optional<User> userOptional = userRepository.findAll().stream().filter(predicate).findFirst();
        if(userOptional.isPresent())
            return ResponseEntity.ok(userOptional.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping(path="/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//        User savedUser = userRepository.addUser(user);
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
        //return ResponseEntity.created(null).build();
    }

    @DeleteMapping(path = "/jpa/users/delete/{id}")
    public void deleteById(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/post")
    public List<Post> getAllPostsForUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id: "+id);

        return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody@Valid Post post){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id: "+id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/jpa/users/{id}/post/{post_id}")
    public Post getPostOfUserById(@PathVariable int id, @PathVariable int post_id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("user is not present for id: "+id);
        List<Post> postList = user.get().getPosts();
        Predicate<? super Post> predicate = post->post.getId() == post_id;
        Optional<Post> matchingPost = postList.stream().filter(predicate).findFirst();
        if(matchingPost.isEmpty())
            throw new UserNotFoundException("Post of the is not available for id:"+id);
        return matchingPost.get();
    }
}
