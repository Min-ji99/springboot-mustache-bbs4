package com.mustache.bbs4.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOTFOUND_HOSPITAL_ID(HttpStatus.NOT_FOUND, "hospital id가 존재하지 않습니다."),
    NOTFOUND_USER_NAME(HttpStatus.NOT_FOUND, "username이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "password가 틀렸습니다."),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "중복된 사용자입니다.");

    HttpStatus httpStatus;
    String message;
}
