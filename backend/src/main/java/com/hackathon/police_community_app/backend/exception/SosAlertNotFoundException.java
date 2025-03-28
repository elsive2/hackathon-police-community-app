package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class SosAlertNotFoundException extends BaseException {
    public SosAlertNotFoundException() {
        super("Sos alert not found", HttpStatus.NOT_FOUND);
    }
}
