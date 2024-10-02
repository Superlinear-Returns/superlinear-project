package com.eight.user.module.service;

import com.eight.user.module.model.User;
import com.eight.user.module.to.LoginTO;
import com.eight.user.module.to.RegisterTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    UserDetails login(LoginTO loginTO);

    User register(RegisterTO registerTO);
}
