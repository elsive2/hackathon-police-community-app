package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends BaseException{
    public RefreshTokenExpiredException() {
        super("Refresh токен устарел", HttpStatus.BAD_REQUEST);
    }
}
