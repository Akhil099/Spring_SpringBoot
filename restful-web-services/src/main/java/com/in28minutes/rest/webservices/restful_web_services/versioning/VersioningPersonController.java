package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping(path="/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path ="/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping(path ="/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping(path ="/person/headers", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeaderParameter(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path ="/person/headers", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeaderParameter(){
        return new PersonV2(new Name("Bob","Charlie"));
    }

    @GetMapping(path ="/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptParameter(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path ="/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptParameter(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
