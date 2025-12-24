package com.rest_api.raj.UserBean;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_api.raj.Post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

//@JsonIgnoreProperties("id")
@JsonFilter("User")
@Entity
@Table(name = "USER_DETAILS")
public class User {  

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message="name can't be empty")
    @JsonProperty("user name")
    @Column(name="username")
    private String name;
    
    //@JsonIgnore
    @Email
    @Column(name = "email")
    private String email;
    @Past
    @JsonProperty("Date of Birth")
    private LocalDate birthDate;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Post> post;


    public User(Integer id , String name , String email , LocalDate birthDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public User(){}

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }

    public List<Post> getPost(){
        return post;
    }

    @Override
    public String toString() {
        return String.format(
            "Id is %d%nName is %s%nEmail is %s%nDate of birth is %s",
            id,
            name,
            email,
            birthDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }

}
