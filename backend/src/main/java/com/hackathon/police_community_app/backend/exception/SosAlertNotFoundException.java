package com.hackathon.police_community_app.backend.exception;

import org.springframework.http.HttpStatus;

// @TODO: Сделать имена на русском
public class SosAlertNotFoundException extends BaseException {
    public SosAlertNotFoundException() {
        super("Запись SOS не найдена", HttpStatus.NOT_FOUND);
    }
}
