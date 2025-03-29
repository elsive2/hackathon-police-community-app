package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class SmsCodeExpiredException extends BaseException{
    public SmsCodeExpiredException() {
        super("Время активации кода истекло", HttpStatus.BAD_REQUEST);
    }
}
