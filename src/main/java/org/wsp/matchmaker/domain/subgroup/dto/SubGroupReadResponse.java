package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SubGroupReadResponse {
    private UUID subgroupId; // 소모임 ID (UUID)
    private String name; // 소모임 이름 (subgroupName을 name으로 변경)
    private String description;  // 소모임 설명
    private String groupName;    // 상위 그룹 이름
    private String status;       // 소모임 상태
    private LocalDateTime createdAt; // 생성일시
    private LocalDateTime updatedAt; // 수정일시
    private List<MemberReadResponse> members; // 소모임 구성원 정보
    private List<ActivityDetailResponse> activities; // 소모임 활동 정보
}