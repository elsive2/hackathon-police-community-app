package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class InvalidSmsCodeException extends BaseException {
    public InvalidSmsCodeException() {
        super("Invalid code", HttpStatus.BAD_REQUEST);
    }
}
