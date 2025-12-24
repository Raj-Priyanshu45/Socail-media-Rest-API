package com.rest_api.raj.Post;

import java.util.List;

public class PostResponse {
    
    private Integer id;
    private String userName;
    private List<Post> allPosts;

    public PostResponse(Integer id , String userName , List<Post> allPosts){
        this.id = id;
        this.userName = userName;
        this.allPosts = allPosts;
    }

    public PostResponse(){}

    public void setId(Integer id){ 
        this.id = id; 
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setAllPosts(List<Post> allPosts){
        this.allPosts = allPosts;
    }

    public Integer getId(){ 
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public List<Post> getAllPosts(){
        return allPosts;
    }
}
