package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedFileTypeException extends BaseException {

    public UnsupportedFileTypeException() {
        super("Unsupported file type", HttpStatus.BAD_REQUEST);
    }
}
