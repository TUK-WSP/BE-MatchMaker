package org.wsp.matchmaker.domain.subgroup.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;


public interface SubGroupRepository extends JpaRepository<SubGroup, UUID> {
    boolean existsBySubGroupId(UUID uuid);
}
