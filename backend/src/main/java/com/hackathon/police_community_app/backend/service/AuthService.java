package com.hackathon.police_community_app.backend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void requestCode(String phoneNumber);

    String verifyCode(String phoneNumber, String code);
}
