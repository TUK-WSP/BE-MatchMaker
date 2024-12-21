package org.wsp.matchmaker.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.user.entity.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByHobbyName(String hobbyName);
}
