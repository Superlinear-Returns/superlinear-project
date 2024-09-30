package com.eight.user.module.controller;

import com.eight.user.module.model.User;
import com.eight.user.module.service.UserService;
import com.eight.user.module.to.LoginTO;
import com.eight.user.module.to.RegisterTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody @Valid LoginTO loginTO) {
        UserDetails user = userService.login(loginTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterTO registerTO) {
        return ResponseEntity.ok(userService.register(registerTO));
    }
}
