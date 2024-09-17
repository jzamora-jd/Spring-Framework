package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
@RequestMapping("/users")
public class Consume_RestTemplate {

    private final String URI = "https://jsonplaceholder.typicode.com/users";//consuming this URI

    private final RestTemplate restTemplate; //Inject ready methods

    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping//GET http type from 3rd party
    public User[] readAllUsers(){

        ResponseEntity<User[]>  responseEntity = restTemplate.getForEntity(URI,User[].class);

        return responseEntity.getBody();

    }

    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){//will consume the data of a specific id from 3rd party website

        String URL = URI + "/{id}";

        return restTemplate.getForObject(URL,Object.class,id);
    }


    @GetMapping("/test")//consuming data and sending a required header app-id
    public ResponseEntity<Object> consumeFromDummyApi(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","6298ebfecd0551211fce37a6");

        HttpEntity<String> entity = new HttpEntity<>(headers);//sending headers

        ResponseEntity<Object> response = restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET,entity,Object.class);

        return response;

    }
}











