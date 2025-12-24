package com.rest_api.raj.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest_api.raj.Exception.UserNotException;
import com.rest_api.raj.Repo.UserRepo;
import com.rest_api.raj.UserBean.User;

@Service
public class UserService {
    
    private final UserRepo repo;
    public UserService(UserRepo repo){
        this.repo = repo;
    }

    public List<User> retreiveAllUsers(){
        return repo.findAll();
    }

    public User retUser(Integer id){
    
        return repo.findById(id).orElseThrow(()-> new UserNotException("User Not Found"));
    }

    public User save(User user){
        return repo.save(user);
    }

    public void delUser(Integer id){

        if(!repo.existsById(id)){
            throw new UserNotException("User Not Found");
        }
        repo.deleteById(id);
    }

    public String retName(Integer id){
        
        if(!repo.existsById(id)){
            throw new UserNotException("User Not Found");
        }

        return repo.findUserNameById(id);
    }
}
