package com.dev0kch.mybook.controller;

import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("users/get/{id}")
    public User getUserById(@PathVariable("id") Long id){
        User user = userRepository._findById(id);
        user.setPassword("");
        return user;
    }
}
