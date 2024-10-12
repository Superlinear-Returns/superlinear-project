package com.eight.user.module.repository;

import com.eight.user.module.model.Blacklist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlacklistRepo extends JpaRepository<Blacklist, Integer> {

    @Query("select b from Blacklist b where b.ipAddress = ?1")
    Optional<Blacklist> findByIpAddress(String ipAddress);
}
