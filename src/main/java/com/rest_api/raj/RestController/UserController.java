package com.rest_api.raj.RestController;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest_api.raj.Post.Post;
import com.rest_api.raj.Post.PostResponse;
import com.rest_api.raj.Service.PostService;
import com.rest_api.raj.Service.UserService;
import com.rest_api.raj.UserBean.User;

import jakarta.validation.Valid;

@RestController
public class UserController {
    
    private final UserService dao;
    private final MessageSource messageSource;
    private final PostService postService;
    public UserController(UserService dao , MessageSource messageSource , PostService postService){
        this.dao = dao;
        this.messageSource = messageSource;
        this.postService = postService;
    }

    @GetMapping("/users")
    public MappingJacksonValue getUsers() {

        List<User> users = dao.retreiveAllUsers();

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.serializeAll();

        FilterProvider filters =
                new SimpleFilterProvider().addFilter("User", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }


    @GetMapping("/users/{id}")
public MappingJacksonValue getUser(@PathVariable Integer id){

    User user = dao.retUser(id);

    EntityModel<User> entityModel = EntityModel.of(user);

    entityModel.add(
        linkTo(methodOn(UserController.class).getUser(id)).withSelfRel()
    );

    entityModel.add(
        linkTo(methodOn(UserController.class).getUsers()).withRel("all-users")
    );

    SimpleBeanPropertyFilter filter =
            SimpleBeanPropertyFilter.filterOutAllExcept("id","user name");

    FilterProvider filters =
            new SimpleFilterProvider().addFilter("User", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(entityModel);
    mapping.setFilters(filters);

    return mapping;
}


    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user){

        User savedUser = dao.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        
        dao.delUser(id);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/in18")
    public String getInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(
            "good.morning.message",
            null,
            "Default Message",
            locale
        );
    }

    @GetMapping("users/{id}/posts")
    public PostResponse retrieveAllPosts(@PathVariable Integer id){
        List<Post> posts =  postService.retPosts(id);
        String name = dao.retName(id);
        PostResponse postResponse = new PostResponse();
        postResponse.setId(id);
        postResponse.setUserName(name);
        postResponse.setAllPosts(posts);

        return postResponse;
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<PostResponse> createPost(@PathVariable Integer id , @Valid @RequestBody Post post){
        Post savedPost = postService.save(id , post);
        String name = dao.retName(id);

        PostResponse postResponse = new PostResponse();
        postResponse.setId(id);
        postResponse.setUserName(name);
        postResponse.setAllPosts(List.of(savedPost));

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{postId}")
        .buildAndExpand(savedPost.getPostId())
        .toUri();

        return ResponseEntity.created(location).body(postResponse);

    } 

    @DeleteMapping("users/{id}/posts/{postId}")
    public ResponseEntity<Void> deleteSpecificPost(@PathVariable Integer id ,
        @PathVariable Long postId){

        postService.deletePost(id , postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("users/{id}/posts")
    public ResponseEntity<Void> deleteAllPostOfUser(@PathVariable Integer id){

        postService.deleteAllPost(id);

        return ResponseEntity.noContent().build();
    }
}
