package com.eight.user.module.controller;

import com.eight.user.module.service.ProfileSettingService;
import com.eight.user.module.to.ProfileSettingTO;
import com.eight.user.module.to.UserProfile;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/profile")
@RestController
public class ProfileSettingController {

    private final ProfileSettingService profileSettingService;

    public ProfileSettingController(ProfileSettingService profileSettingService) {
        this.profileSettingService = profileSettingService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable("userId") @Valid Integer userId) {
        return ResponseEntity.ok(profileSettingService.getUserProfile(userId));
    }

    @PutMapping
    public ResponseEntity<Void> editUserProfile(@RequestBody @Valid ProfileSettingTO profileSettingTO) {
        profileSettingService.editUserProfile(profileSettingTO);
        return ResponseEntity.ok().build();
    }
}
