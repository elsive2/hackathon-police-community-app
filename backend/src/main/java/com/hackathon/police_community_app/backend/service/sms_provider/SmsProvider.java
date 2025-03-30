package com.hackathon.police_community_app.backend.service.sms_provider;

public interface SmsProvider {
    String sendSms(String phoneNumber, String code);
}
