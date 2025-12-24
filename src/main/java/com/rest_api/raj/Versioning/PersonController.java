package com.rest_api.raj.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    
    @GetMapping("/person-v1")
    public PersonV1 firstVersionByURL(){
        return new PersonV1("Yash Chaudhary");
    }

    @GetMapping("/person-v2")
    public PersonV2 secondVersionByURL(){
        return new PersonV2("Yash", "Chaudhary");
    }

     @GetMapping(path="/person",params="v=1")
    public PersonV1 firstVersionByParam(){
        return new PersonV1("Yash Chaudhary");
    }

    @GetMapping(path="/person" , params="v=2")
    public PersonV2 secondVersionByParam(){
        return new PersonV2("Yash", "Chaudhary");
    }

    @GetMapping(path="/person/header" , headers="X-API-Version=1")
    public PersonV1 firstVersionByHeaders(){
        return new PersonV1("Yash Chaudhary");
    }

    @GetMapping(path="/person/header" , headers="X-API-Version=2")
    public PersonV2 secondVersionByHeader(){
        return new PersonV2("Yash", "Chaudhary");
    }

    @GetMapping(path="/person/accept" , produces="application/vnd.raj.person-v1+json")
    public PersonV1 firstVersionByAcceptHeaders(){
        return new PersonV1("Yash Chaudhary");
    }

    @GetMapping(path="/person/accept" , produces="application/vnd.raj.person-v2+json")
    public PersonV2 secondVersionByAcceptHeader(){
        return new PersonV2("Yash", "Chaudhary");
    }
}
