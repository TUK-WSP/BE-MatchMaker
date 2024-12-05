package org.wsp.matchmaker.domain.admin.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportStatus {
    UNDER_REVIEW("심사중"),
    APPROVED("승인"),
    REJECTED("반려");

    private final String description;
}
