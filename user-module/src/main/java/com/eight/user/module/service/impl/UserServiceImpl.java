package com.eight.user.module.service.impl;

import com.eight.user.module.model.User;
import com.eight.user.module.repository.IUserRepo;
import com.eight.user.module.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final IUserRepo userRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

}
