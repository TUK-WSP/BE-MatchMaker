package org.wsp.matchmaker.domain.recruitment.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.recruitment.entity.RecruitmentApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecruitmentApplicationRepository extends JpaRepository<RecruitmentApplication, Long> {

    // 사용자 ID로 모집 신청 목록 가져오기
    List<RecruitmentApplication> findByUser_UserId(UUID userId);

    // 모집 신청 ID로 찾기
    public Optional<RecruitmentApplication> findById(Long applicationId);

}