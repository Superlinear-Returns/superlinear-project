package com.eight.user.module.repository;

import com.eight.user.module.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepo extends JpaRepository<UserRole, Integer> {

    @Modifying
    @Query("delete from UserRole u where u.roleId = ?1 and u.userId = ?2")
    void deleteByRoleIdAndUserId(Integer roleId, Integer userId);
}
