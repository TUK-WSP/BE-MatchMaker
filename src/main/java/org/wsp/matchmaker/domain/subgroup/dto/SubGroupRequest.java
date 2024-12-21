package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SubGroupRequest {
    private UUID groupId; // 상위 그룹 ID
    private UUID subgroupId;
    private String subgroupName; // 소모임 이름
    private String description; // 소모임 설명
    private String status;
}