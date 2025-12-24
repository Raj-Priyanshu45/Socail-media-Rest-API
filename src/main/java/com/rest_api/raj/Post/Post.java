package com.rest_api.raj.Post;

import com.rest_api.raj.UserBean.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long postId;
    
    @NotBlank
    private String description;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Post(String description , User user){
        this.description = description;
        this.user = user;
    }

    protected Post(){}

    public void setDescription(String description){
        this.description = description;
    }


    public String getDescription(){
        return description;
    }

    public Long getPostId(){
        return postId;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
