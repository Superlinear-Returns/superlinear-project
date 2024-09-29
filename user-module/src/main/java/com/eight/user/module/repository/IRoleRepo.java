package com.eight.user.module.repository;

import com.eight.user.module.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Integer> {

    @Query("select r.roleId from Role r where r.roleType = ?1")
    Integer findIdByRoleType(String roleType);
}
