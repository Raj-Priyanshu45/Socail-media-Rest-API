package com.rest_api.raj.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest_api.raj.Exception.UserNotException;
import com.rest_api.raj.Post.Post;
import com.rest_api.raj.Post.PostRepo;
import com.rest_api.raj.Repo.UserRepo;
import com.rest_api.raj.UserBean.User;

@Service
public class PostService {
    
    private PostRepo postRepo;
    private UserRepo userRepo;
    
    public PostService(PostRepo postRepo , UserRepo userRepo){
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    public List<Post> retPosts(Integer id){

        if(!postRepo. existsByUser_Id(id)){
            throw new UserNotException("User Not Found :"+id);
        }

        return postRepo.findByUser_Id(id);
    }

    public Post save(Integer id , Post post){
        
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotException("User not found: " + id));

        post.setUser(user);
        return postRepo.save(post);
    }

    public void deletePost(Integer userId, Long postId) {
        Post post = postRepo.findByUser_IdAndPostId(userId, postId)
                .orElseThrow(() -> new UserNotException("Post not found"));

        postRepo.delete(post);
    }

    public void deleteAllPost(Integer id){
        postRepo.deleteByUser_Id(id);
    }
}
