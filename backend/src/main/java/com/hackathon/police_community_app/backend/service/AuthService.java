package com.hackathon.police_community_app.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface AuthService {
    void requestCode(final String phone);

    String verifyCode(final String phone, final String code);

    UserDetails getByUsername(String username);
}
