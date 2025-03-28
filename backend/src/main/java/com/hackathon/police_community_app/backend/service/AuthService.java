package com.hackathon.police_community_app.backend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void requestCode(final String phone);

    String verifyCode(final String phone, final String code);

    UserDetails getByPhone(String username);
}
