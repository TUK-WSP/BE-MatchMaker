package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ActivityDetailResponse {
    private UUID activityId; // 활동 ID
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간
    private String subgroupName; // 소모임 이름 (추가 정보)

    // 기본 생성자 및 빌더 패턴을 사용하여 생성자를 자동 생성
    // 수동 생성자는 이제 필요없습니다.
}