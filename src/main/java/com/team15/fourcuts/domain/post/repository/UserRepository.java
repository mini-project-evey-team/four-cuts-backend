package com.team15.fourcuts.domain.post.repository;

import com.team15.fourcuts.domain.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
