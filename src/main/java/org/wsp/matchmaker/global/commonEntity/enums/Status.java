package org.wsp.matchmaker.global.commonEntity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    PENDING("대기 중"),
    DELETED("삭제됨");

    private final String description;
}

