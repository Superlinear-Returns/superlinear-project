package com.eight.user.module.controller;

import com.eight.user.module.model.User;
import com.eight.user.module.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId).orElse(null));
    }
}
