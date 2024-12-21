package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SubGroupResponse {
    private UUID subgroupId; // 소모임 ID
    private UUID groupId; // 상위 그룹 ID
    private String subgroupName; // 소모임 이름
    private String description; // 소모임 설명
    private String status; // 소모임 상태
    private LocalDateTime createdAt; // 생성일시
    private LocalDateTime updatedAt; // 수정일시
}