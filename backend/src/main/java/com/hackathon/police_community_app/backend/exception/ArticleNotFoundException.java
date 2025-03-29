package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends BaseException{
    public ArticleNotFoundException() {
        super("Статья не найдена", HttpStatus.NOT_FOUND);
    }
}
