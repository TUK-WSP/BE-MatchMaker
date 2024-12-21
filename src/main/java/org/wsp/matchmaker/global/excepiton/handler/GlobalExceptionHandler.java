package org.wsp.matchmaker.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.wsp.matchmaker.global.response.ApiResponse;
import org.wsp.matchmaker.global.response.code.resultCode.ErrorStatus;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 유효성 검사 실패 처리 (MethodArgumentNotValidException과 BindException)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(Exception ex) {
        logger.error("Validation failed: {}", ex.getMessage());
        String errorMessage;
        if (ex instanceof MethodArgumentNotValidException) {
            errorMessage = ((MethodArgumentNotValidException) ex).getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.joining(", "));
        } else {
            // BindException 처리
            errorMessage = ((BindException) ex).getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        }
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), errorMessage, null);
        return new ResponseEntity<>(response, ErrorStatus.BAD_REQUEST.getHttpStatus());
    }

    // 요청 본문 읽기 실패 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException: {}", ex.getMessage());
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), "요청 본문을 읽는 데 실패했습니다.", null);
        return new ResponseEntity<>(response, ErrorStatus.BAD_REQUEST.getHttpStatus());
    }

    // NoSuchElementException 처리 -> NOT_FOUND
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoSuchElementException(NoSuchElementException ex) {
        logger.error("NoSuchElementException: {}", ex.getMessage());
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.NOT_FOUND.getCode(), ex.getMessage(), null);
        return new ResponseEntity<>(response, ErrorStatus.NOT_FOUND.getHttpStatus());
    }

    // IllegalArgumentException 처리 -> BAD_REQUEST
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException: {}", ex.getMessage());
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), ex.getMessage(), null);
        return new ResponseEntity<>(response, ErrorStatus.BAD_REQUEST.getHttpStatus());
    }

    // AccessDeniedException 처리 -> FORBIDDEN
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("AccessDeniedException: {}", ex.getMessage());
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.FORBIDDEN.getCode(), ErrorStatus.FORBIDDEN.getMessage(), null);
        return new ResponseEntity<>(response, ErrorStatus.FORBIDDEN.getHttpStatus());
    }

    // 기타 모든 예외 처리 -> INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        logger.error("Unhandled exception occurred", ex);
        ApiResponse<Object> response = ApiResponse.onFailure(ErrorStatus.INTERNAL_SERVER_ERROR.getCode(), ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(), null);
        return new ResponseEntity<>(response, ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}
