package com.eight.user.module.service;

import com.eight.user.module.to.ProfileSettingTO;
import com.eight.user.module.to.UserProfile;

public interface ProfileSettingService {

    UserProfile getUserProfile(Integer userId);

    void editUserProfile(ProfileSettingTO profileSettingTO);
}
