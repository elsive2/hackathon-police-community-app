package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class SmsCodeExpiredException extends BaseException{
    public SmsCodeExpiredException() {
        super("Code expired", HttpStatus.BAD_REQUEST);
    }
}
