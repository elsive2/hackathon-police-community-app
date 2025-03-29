package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

public class SosAlertIsOccupiedException extends BaseException {
    public SosAlertIsOccupiedException() {
        super("Запись SOS уже занята", HttpStatus.BAD_REQUEST);
    }
}
