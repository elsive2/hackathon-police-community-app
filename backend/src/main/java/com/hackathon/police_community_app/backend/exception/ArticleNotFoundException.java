package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends BaseException{
    public ArticleNotFoundException() {
        super("Article not found", HttpStatus.NOT_FOUND);
    }
}
