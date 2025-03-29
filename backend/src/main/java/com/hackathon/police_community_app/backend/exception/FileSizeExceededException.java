package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class FileSizeExceededException extends BaseException{
    public FileSizeExceededException() {
        super("File size exceeded", HttpStatus.BAD_REQUEST);
    }
}
