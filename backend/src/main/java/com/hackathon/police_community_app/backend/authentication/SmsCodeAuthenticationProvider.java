package com.hackathon.police_community_app.backend.authentication;

import com.hackathon.police_community_app.backend.entity.SmsCode;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Role;
import com.hackathon.police_community_app.backend.exception.InvalidSmsCodeException;
import com.hackathon.police_community_app.backend.exception.SmsCodeExpiredException;
import com.hackathon.police_community_app.backend.repository.SmsCodeRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
@AllArgsConstructor
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private SmsCodeRepository smsCodeRepository;

    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsAuth = (SmsCodeAuthenticationToken) authentication;

        String phoneNumber = smsAuth.getPhoneNumber();
        String code = (String) smsAuth.getCredentials();

        SmsCode smsCode = smsCodeRepository.findByPhoneNumberAndCodeOptional(phoneNumber, code)
                .orElseThrow(InvalidSmsCodeException::new);

        if (smsCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new SmsCodeExpiredException();
        }

        User user = userRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setPhoneNumber(phoneNumber)
                            .setRole(Role.ROLE_ADMIN);
                    return userRepository.save(newUser);
                });

        return new SmsCodeAuthenticationToken(
                user.getId(),
                user.getPhoneNumber(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
