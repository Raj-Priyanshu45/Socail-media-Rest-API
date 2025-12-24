package com.rest_api.raj.Versioning;

public class PersonV2 {
    
    private final String firstName;
    private final String lastName;

    public PersonV2(String firstName , String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    @Override
    public String toString(){

        return String.format("First name is :%s \n Last name is :%s", firstName , lastName);
    }
}
