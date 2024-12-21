package org.wsp.matchmaker.domain.subgroup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class MemberReadResponse {
    private UUID userId;      // 사용자 ID
    private String userName;  // 사용자 이름
    private LocalDateTime joinedDate; // 가입 날짜
}