package org.wsp.matchmaker.domain.admin.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportType {
    USER("유저"),
    GROUP("모임"),
    SUBGROUP("소모임");

    private final String description;
}
