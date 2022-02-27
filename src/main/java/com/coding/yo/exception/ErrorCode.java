package com.coding.yo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * error code 추가 필요 (API 명세서 참고)
 * 예외 정보를 담은 enum class
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    EXIST_USER(HttpStatus.BAD_REQUEST, "이미 등록된 유저입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "해당 요청은 로그인이 필요합니다."),
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "해당 요청에 권한이 없습니다."),
    INVALID_AUTHORIZATION(HttpStatus.BAD_REQUEST, "인증 정보가 부정확합니다."),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND,"해당 댓글을 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
