package com.hackathon.police_community_app.backend.service;

public interface SmsService {
    void sendSms(String phoneNumber, String code);
}
