package com.eight.user.module.service;

import com.eight.user.module.to.LoginTO;
import com.eight.user.module.to.RegisterTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    UserDetails login(LoginTO loginTO);

    UserDetails register(RegisterTO registerTO);
}
