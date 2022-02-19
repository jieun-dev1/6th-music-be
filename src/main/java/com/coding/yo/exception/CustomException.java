package com.coding.yo.exception;

import com.google.firebase.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    public CustomException(ErrorCode errorCode) { this.errorCode = errorCode; }
    public CustomException(ErrorCode errorCode, String message) {
         super(message);
        this.errorCode = errorCode;
    }
}