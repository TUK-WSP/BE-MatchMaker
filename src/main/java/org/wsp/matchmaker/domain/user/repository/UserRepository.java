package org.wsp.matchmaker.domain.user.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.user.entity.User;

public interface UserRepository extends JpaRepository <User, UUID>{
    boolean existsByUserId(UUID userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserName(String userName);
}
