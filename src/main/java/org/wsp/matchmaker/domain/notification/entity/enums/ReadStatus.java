package org.wsp.matchmaker.domain.notification.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReadStatus {
    READ("읽음"),
    UNREAD("읽지 않음");
    private final String description;
}

