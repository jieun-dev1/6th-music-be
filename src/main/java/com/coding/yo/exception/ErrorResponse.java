package com.coding.yo.exception;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

/**
 * 예외에 대한 응답 정보 저장
 * 인자가 많기 때문에/상태에 매칭되는 숫자코드를 외울 필요가 없어서 Builder 사용.
*/
@Getter
@Builder
public class ErrorResponse {
    private final OffsetDateTime time = OffsetDateTime.now();
    private final String error;
    private final String code; //예외를 세분화하기 위한 사용자 지정 코드
    private final String detail; // code 의 세부 내용
    private final String message; //예외 메시지 저장
    private final int status; //HTTP 상태 값 저장 400, 404, 500 등.

    public static ResponseEntity<ErrorResponse> toResponseEntity(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .detail(errorCode.getDetail())
                        .message(e.getMessage())
                        .build());

}
    //
}
