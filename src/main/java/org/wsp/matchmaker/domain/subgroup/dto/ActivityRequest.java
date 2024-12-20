package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ActivityRequest {
    private UUID subGroupId; // 소모임 ID
    private String title; // 활동 제목
    private String content; // 활동 내용
}