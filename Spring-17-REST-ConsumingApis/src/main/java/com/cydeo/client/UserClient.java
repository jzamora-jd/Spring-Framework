package com.cydeo.client;

import com.cydeo.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com/",name = "USER-CLIENT") //consuming microservices must have Client interface
public interface UserClient {//@FeignClient which api we will consume

    @GetMapping("/users")
    List<User> getUsers();//will execute url above + /users when its called - 3rd party
}
