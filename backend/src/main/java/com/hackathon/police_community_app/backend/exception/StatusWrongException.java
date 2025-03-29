package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class StatusWrongException extends BaseException{
    public StatusWrongException() {
        super("Status is invalid", HttpStatus.BAD_REQUEST);
    }
}
