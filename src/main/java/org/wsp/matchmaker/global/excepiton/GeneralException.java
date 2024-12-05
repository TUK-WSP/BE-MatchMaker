package org.wsp.matchmaker.global.excepiton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wsp.matchmaker.global.response.code.BaseErrorCode;
import org.wsp.matchmaker.global.response.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReasonDTO getErrorReason() {
        return this.errorCode.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.errorCode.getReasonHttpStatus();
    }
}
