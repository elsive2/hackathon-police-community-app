package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.response.TokenResponse;

public interface AuthService {
    void requestCode(String phoneNumber);

    TokenResponse verifyCode(String phoneNumber, String code);

    TokenResponse verifyRefreshToken(String rawToken);
}