package com.furmi.airline.controller;

import com.furmi.airline.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }

//    @GetMapping("/user")
//    public User getUser(@RequestParam(value = "name") String name) {
//        return new User(name, 20);
//    }

}
