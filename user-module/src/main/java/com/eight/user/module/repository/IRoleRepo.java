package com.eight.user.module.repository;

import com.eight.user.module.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT R.* FROM ROLE R "
            + "JOIN USER_ROLE U "
            + "ON R.ROLE_ID = U.ROLE_ID "
            + "WHERE U.USER_ID = :userId", nativeQuery = true)
    List<Role> findByUserId(Integer userId);

    @Query("select r.roleId from Role r where r.roleType = ?1")
    Integer findIdByRoleType(String roleType);
}
