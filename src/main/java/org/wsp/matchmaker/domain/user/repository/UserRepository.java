package org.wsp.matchmaker.domain.user.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.user.entity.User;

public interface UserRepository extends JpaRepository <User, UUID>{
    Optional<User> findByUserId(UUID userId);
    Optional<User> findByUserEmail(String userEmail);

    boolean existsByUserId(UUID userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserName(String userName);
}
