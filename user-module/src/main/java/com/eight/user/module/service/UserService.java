package com.eight.user.module.service;

import com.eight.user.module.model.User;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Integer userId);
}
