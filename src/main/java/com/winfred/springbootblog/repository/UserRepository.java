package com.winfred.springbootblog.repository;

import com.winfred.springbootblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String Email);

    Optional<User> findByUsernameOrEmail(String username, String Email);

    Optional<User> findByUsername(String Username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String Email);


}
