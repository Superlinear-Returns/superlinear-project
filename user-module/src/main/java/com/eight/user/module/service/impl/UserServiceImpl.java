package com.eight.user.module.service.impl;

import static com.eight.user.module.constant.RoleType.*;

import com.eight.user.module.constant.StatusCode;
import com.eight.user.module.core.exception.BaseException;
import com.eight.user.module.model.Role;
import com.eight.user.module.model.User;
import com.eight.user.module.model.UserRole;
import com.eight.user.module.repository.IRoleRepo;
import com.eight.user.module.repository.IUserRepo;
import com.eight.user.module.repository.IUserRoleRepo;
import com.eight.user.module.service.UserService;
import com.eight.user.module.to.LoginTO;
import com.eight.user.module.to.RegisterTO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

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
    public UserDetails login(LoginTO loginTO) {
        var username = loginTO.getUsername().toLowerCase().trim();
        var user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(loginTO.getPassword(), user.getPassword())) {
            throw new BaseException(StatusCode.PASSWORD_ERR, "Password is incorrect");
        }
        List<Role> roles = roleRepo.findByUserId(user.getUserId());
        List<GrantedAuthority> authorities = convertToAuthorities(roles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    @Transactional(transactionManager = "userModuleTransactionManager")
    public User register(RegisterTO registerTO) {
        checkDuplicateUser(registerTO);
        var username = registerTO.getUsername().toLowerCase();
        var password = passwordEncoder.encode(registerTO.getPassword());
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(registerTO.getGender());
        userRepo.save(user);

        var roleId = roleRepo.findIdByRoleType(NORMAL_USER.name());
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getUserId());
        userRoleRepo.save(userRole);

        return user;
    }

    private void checkDuplicateUser(RegisterTO registerTO) {
        var username = registerTO.getUsername().toLowerCase();
        if(userRepo.countByUsername(username) > 0) {
            throw new BaseException(StatusCode.DUPLICATE, "Username already be token");
        }
    }

    private List<GrantedAuthority> convertToAuthorities(List<Role> roleList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleType()));
        }
        return authorities;
    }
}
