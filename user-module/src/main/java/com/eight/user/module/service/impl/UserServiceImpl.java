package com.eight.user.module.service.impl;

import static com.eight.user.module.constant.RoleType.*;

import com.eight.user.module.constant.StatusCode;
import com.eight.user.module.core.exception.BaseException;
import com.eight.user.module.model.User;
import com.eight.user.module.model.UserRole;
import com.eight.user.module.repository.IRoleRepo;
import com.eight.user.module.repository.IUserRepo;
import com.eight.user.module.repository.IUserRoleRepo;
import com.eight.user.module.service.UserService;
import com.eight.user.module.to.RegisterTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final IUserRepo userRepo;

    private final IUserRoleRepo userRoleRepo;

    private final IRoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepo userRepo, PasswordEncoder passwordEncoder, IUserRoleRepo userRoleRepo, IRoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional(transactionManager = "userModuleTransactionManager")
    public void register(RegisterTO registerTO) {
        checkDuplicateUser(registerTO);
        var username = registerTO.getUsername().toLowerCase();
        var password = passwordEncoder.encode(registerTO.getPassword());
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(registerTO.getGender());
        userRepo.saveAndFlush(user);

        var roleId = roleRepo.findIdByRoleType(NORMAL_USER.name());
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getUserId());
        userRoleRepo.saveAndFlush(userRole);
    }

    private void checkDuplicateUser(RegisterTO registerTO) {
        var username = registerTO.getUsername().toLowerCase();
        var password = passwordEncoder.encode(registerTO.getPassword());

        if(userRepo.countByUsername(username) > 0) {
            throw new BaseException(StatusCode.DUPLICATE, "Username already be token");
        }

        if (userRepo.countByPassword(password) > 0) {
            throw new BaseException(StatusCode.DUPLICATE, "Password already be token");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
