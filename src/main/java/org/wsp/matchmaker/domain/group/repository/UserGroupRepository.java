package org.wsp.matchmaker.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.group.entity.UserGroup;

import java.util.List;
import java.util.UUID;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUser_UserId(UUID userId); // 사용자가 가입한 모임 조회
}