package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends BaseException {
    public RefreshTokenNotFoundException() {
        super("Refresh токен не найден", HttpStatus.NOT_FOUND);
    }
}
