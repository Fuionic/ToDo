package com.ToDomangemnet.Repository;

import com.ToDomangemnet.Entity.USer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<USer, Long >{
    Optional<USer> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<USer> findByUsernameOrEmail(String username, String email);
}
