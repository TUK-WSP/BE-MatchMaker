package org.wsp.matchmaker.domain.group.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wsp.matchmaker.domain.group.entity.Group;
import org.wsp.matchmaker.domain.user.entity.Hobby;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    boolean existsByGroupId(UUID groupId);
}
