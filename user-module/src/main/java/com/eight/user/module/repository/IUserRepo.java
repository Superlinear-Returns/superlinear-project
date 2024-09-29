package com.eight.user.module.repository;

import com.eight.user.module.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {

    @Query("select count(u) from User u where u.username = ?1")
    int countByUsername(String username);

    @Query("select count(u) from User u where u.password = ?1")
    int countByPassword(String password);
}