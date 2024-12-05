package org.wsp.matchmaker.global.response.code;

import lombok.Getter;

public interface BaseCode {
    public ReasonDTO getReason();

    public ReasonDTO getReasonHttpStatus();
}