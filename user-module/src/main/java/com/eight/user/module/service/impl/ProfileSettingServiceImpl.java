package com.eight.user.module.service.impl;

import com.eight.user.module.constant.StatusCode;
import com.eight.user.module.core.exception.BaseException;
import com.eight.user.module.repository.IUserRepo;
import com.eight.user.module.service.ProfileSettingService;
import com.eight.user.module.to.ProfileSettingTO;
import com.eight.user.module.to.UserProfile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProfileSettingServiceImpl implements ProfileSettingService {

    private final IUserRepo userRepo;

    public ProfileSettingServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserProfile getUserProfile(Integer userId) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new BaseException(StatusCode.USER_NOT_FOUND, "User not found"));

        var userProfile = new UserProfile();
        userProfile.setUsername(user.getUsername());
        userProfile.setPhoneNumber(StringUtils.defaultIfBlank(user.getPhoneNumber(), "-"));
        userProfile.setEmail(StringUtils.defaultIfBlank(user.getEmail(), "-"));
        return userProfile;
    }

    @Override
    public void editUserProfile(ProfileSettingTO profileSettingTO) {
        var user = userRepo.findById(profileSettingTO.getUserId())
                .orElseThrow(() -> new BaseException(StatusCode.USER_NOT_FOUND, "User not found"));

        // if username would not be changed, then fe will not send username
        var username = StringUtils.lowerCase(profileSettingTO.getUsername());
        if (StringUtils.isNotBlank(username)) {
            var count = userRepo.countByUsername(username.trim());
            if (count > 0) {
                throw new BaseException(StatusCode.DUPLICATE, "Username already exist");
            }
            user.setUsername(username.trim());
        }

        // pre-check for email and phone number needed
        if (StringUtils.isNotBlank(profileSettingTO.getEmail())) {
            user.setEmail(StringUtils.trim(profileSettingTO.getEmail()));
        }

        if (StringUtils.isNotBlank(profileSettingTO.getPhoneNumber())) {
            user.setPhoneNumber(StringUtils.trim(profileSettingTO.getPhoneNumber()));
        }
        userRepo.save(user);
    }

}
