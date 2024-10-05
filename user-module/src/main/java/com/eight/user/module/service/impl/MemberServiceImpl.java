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
import com.eight.user.module.service.MemberService;
import com.eight.user.module.to.LoginTO;
import com.eight.user.module.to.RegisterTO;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final IUserRepo userRepo;

    private final IUserRoleRepo userRoleRepo;

    private final IRoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final ConcurrentHashMap<String, LoginAttemptRecord> loginAttemptCache = new ConcurrentHashMap<>();

    public MemberServiceImpl(IUserRepo userRepo, PasswordEncoder passwordEncoder, IUserRoleRepo userRoleRepo, IRoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
    }

    @Getter
    @Setter
    private static class LoginAttemptRecord {
        private int attempt;
        private boolean isLocked;
        private LocalDateTime lastAttemptTime;

        public void incrementAttempt() {
            attempt++;
        }
    }

    @Override
    public UserDetails login(LoginTO loginTO) {
        var username = loginTO.getUsername().toLowerCase().trim();
        var user = userRepo.findByUsername(username)
                .orElseThrow(() -> new BaseException(StatusCode.USER_NOT_FOUND, String.format("User not found with username: [%s]", username)));
        if (!passwordEncoder.matches(loginTO.getPassword(), user.getPassword())) {
            throw new BaseException(StatusCode.PASSWORD_ERR, String.format("Password not match for user: [%s]", username));
        }
        user.setLastLoginDate(LocalDateTime.now());
        userRepo.save(user);

        List<Role> roles = roleRepo.findByUserId(user.getUserId());
        List<GrantedAuthority> authorities = convertToAuthorities(roles);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    @Transactional(transactionManager = "userModuleTransactionManager")
    public UserDetails register(RegisterTO registerTO) {
        checkDuplicateUser(registerTO);
        var username = registerTO.getUsername().toLowerCase();
        var password = passwordEncoder.encode(registerTO.getPassword());
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(registerTO.getGender());
        user.setDateOfBirth(Date.valueOf(registerTO.getDateOfBirth()));
        userRepo.save(user);

        var roleId = roleRepo.findIdByRoleType(NORMAL_USER.name());
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getUserId());
        userRoleRepo.save(userRole);
        return login(new LoginTO(username, registerTO.getPassword()));
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
