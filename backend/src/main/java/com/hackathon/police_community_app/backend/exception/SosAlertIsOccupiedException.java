package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class SosAlertIsOccupiedException extends BaseException {
    public SosAlertIsOccupiedException() {
        super("Sos alert is occupied", HttpStatus.BAD_REQUEST);
    }
}
