package com.furmi.airline.controller;

import com.furmi.airline.model.User;
import com.furmi.airline.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }

//    @GetMapping("/user")
//    public User getUser(@RequestParam(value = "name") String name) {
//        return new User(name, 20);
//    }
    @GetMapping("/use/{email}")
    public User getByEmail(@PathVariable String email){
        log.info("Getting user by email {}", email);
        return userService.getUserByEmail(email);
    }
}
