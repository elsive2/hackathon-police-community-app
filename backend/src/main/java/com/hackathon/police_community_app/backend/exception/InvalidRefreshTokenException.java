package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BaseException{
    public InvalidRefreshTokenException() {
        super("Неверный refresh токен", HttpStatus.BAD_REQUEST);
    }
}
