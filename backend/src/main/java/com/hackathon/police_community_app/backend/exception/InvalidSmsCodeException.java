package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class InvalidSmsCodeException extends BaseException {
    public InvalidSmsCodeException() {
        super("Неверный код СМС", HttpStatus.BAD_REQUEST);
    }
}
