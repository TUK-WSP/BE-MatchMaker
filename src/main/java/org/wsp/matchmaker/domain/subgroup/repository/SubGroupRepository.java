package org.wsp.matchmaker.domain.subgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wsp.matchmaker.domain.subgroup.entity.SubGroup;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubGroupRepository extends JpaRepository<SubGroup, UUID> {
    // 이름으로 소모임 검색
    List<SubGroup> findByNameContainingIgnoreCase(String name);
}