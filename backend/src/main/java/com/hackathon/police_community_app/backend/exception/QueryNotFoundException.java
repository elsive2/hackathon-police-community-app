package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class QueryNotFoundException extends BaseException{
    public QueryNotFoundException() {
        super("Запрос не найден", HttpStatus.NOT_FOUND);
    }
}
