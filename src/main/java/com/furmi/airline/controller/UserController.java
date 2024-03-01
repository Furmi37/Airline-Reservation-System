package com.furmi.airline.controller;

import com.furmi.airline.model.User;
import com.furmi.airline.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{email}")
    public User getByEmail(@PathVariable String email){
        log.info("Getting user by email {}", email);
        return userService.getUserByEmail(email);
    }
    @GetMapping("/user")
    public List<User> getAll(){
        log.info("Getting all users");
        return userService.getAllUsers();
    }
    @PostMapping("/user")
    public Long createUser(@RequestBody User user) {
        log.info("Creating user");
        return userService.createUser(user);
    }


}
