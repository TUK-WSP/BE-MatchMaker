package org.wsp.matchmaker.domain.admin.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminRole {
    SUPER_ADMIN("관리자"),
    ADMIN("일반");

    private final String description;
}
