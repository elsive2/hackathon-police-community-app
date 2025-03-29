package com.hackathon.police_community_app.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Value("${twilio.phone.number}")
//    private String twilioPhoneNumber;
//
    public void sendSms(String toPhoneNumber, String code) {
//        Twilio.init(accountSid, authToken);
//        String messageBody = "Ваш код для входа: " + code;
//        Message.creator(
//                new com.twilio.type.PhoneNumber(toPhoneNumber),
//                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
//                messageBody
//        ).create();
    }
}
