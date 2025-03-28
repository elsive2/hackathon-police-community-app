package com.hackathon.police_community_app.backend.authentication;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    @Getter
    private final String phoneNumber;
    private final String code;
    private final Long userId;

    public SmsCodeAuthenticationToken(String phoneNumber, String code) {
        super(null);
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.userId = null;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Long userId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phoneNumber = null;
        this.code = null;
        this.userId = userId;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return code;
    }

    @Override
    public Object getPrincipal() {
        return userId != null ? userId : phoneNumber;
    }

}
