package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super("Пользователь не найден", HttpStatus.NOT_FOUND);
    }
}
