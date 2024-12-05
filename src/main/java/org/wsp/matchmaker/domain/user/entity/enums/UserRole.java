package org.wsp.matchmaker.domain.user.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    Leader("방장"),
    SubLeader("부방장"),
    Member("맴버");

    private final String description;
}
