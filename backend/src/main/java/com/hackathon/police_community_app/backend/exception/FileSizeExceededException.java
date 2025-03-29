package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class FileSizeExceededException extends BaseException{
    public FileSizeExceededException() {
        super("Размер файла превышен", HttpStatus.BAD_REQUEST);
    }
}
