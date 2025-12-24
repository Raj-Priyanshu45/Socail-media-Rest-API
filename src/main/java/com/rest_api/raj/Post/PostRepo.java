package com.rest_api.raj.Post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface  PostRepo extends JpaRepository<Post, Long>{
    boolean existsByUser_Id(Integer userId);

    List<Post> findByUser_Id(Integer userId);

    Optional<Post> findByUser_IdAndPostId(Integer userId, Long postId);

    void deleteByUser_Id(Integer userId);
}
