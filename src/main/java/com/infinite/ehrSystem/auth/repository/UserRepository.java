package com.infinite.ehrSystem.auth.repository;

import com.infinite.ehrSystem.auth.entity.User;
import com.infinite.ehrSystem.auth.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndStatus(
            String username,
            UserStatus status
    );

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRole_NameContainingIgnoreCaseAndStatusOrderByUsernameAsc(
            String roleNameFragment,
            UserStatus status
    );
}
