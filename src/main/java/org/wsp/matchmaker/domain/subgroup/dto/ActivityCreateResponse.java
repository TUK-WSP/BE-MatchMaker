package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ActivityCreateResponse {
    private UUID activityId; // UUID 타입으로 정의
    private UUID subGroupId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}