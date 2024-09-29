package com.eight.user.module.controller;

import com.eight.user.module.model.User;
import com.eight.user.module.service.UserService;
import com.eight.user.module.to.RegisterTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterTO registerTO) {
        userService.register(registerTO);
        return ResponseEntity.ok().build();
    }
}
