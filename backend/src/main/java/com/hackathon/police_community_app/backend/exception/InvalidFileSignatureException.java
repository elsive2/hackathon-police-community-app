package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class InvalidFileSignatureException extends BaseException{
    public InvalidFileSignatureException() {
        super("Не правильная подпись файла", HttpStatus.BAD_REQUEST);
    }
}
