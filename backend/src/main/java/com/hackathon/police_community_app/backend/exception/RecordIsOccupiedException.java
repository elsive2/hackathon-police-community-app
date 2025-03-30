package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class RecordIsOccupiedException extends BaseException {
    public RecordIsOccupiedException() {
        super("Запись уже занята", HttpStatus.BAD_REQUEST);
    }
}
