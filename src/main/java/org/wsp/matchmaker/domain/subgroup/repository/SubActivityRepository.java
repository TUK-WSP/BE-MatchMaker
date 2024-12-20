package org.wsp.matchmaker.domain.subgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wsp.matchmaker.domain.subgroup.entity.SubActivity;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, UUID> {
    List<SubActivity> findBySubGroup_SubGroupId(UUID subGroupId);
}