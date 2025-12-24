package com.rest_api.raj.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest_api.raj.UserBean.User;

public interface  UserRepo  extends JpaRepository<User, Integer>{
    @Query("select u.name from User u where u.id = :id")
    String findUserNameById(@Param("id") Integer id);
}
