package com.rest_api.raj.Versioning;

public class PersonV1 {
    
    private final String name;
    public PersonV1(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return String.format("Name of the person is %s",name);
    }
}
