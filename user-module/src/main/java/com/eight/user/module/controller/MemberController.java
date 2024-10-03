package com.eight.user.module.controller;

import com.eight.user.module.model.User;
import com.eight.user.module.service.MemberService;
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
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody @Valid LoginTO loginTO) {
        return ResponseEntity.ok(memberService.login(loginTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@RequestBody @Valid RegisterTO registerTO) {
        return ResponseEntity.ok(memberService.register(registerTO));
    }
}
