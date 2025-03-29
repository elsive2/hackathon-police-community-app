package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.entity.SmsCode;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Role;
import com.hackathon.police_community_app.backend.repository.SmsCodeRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import com.hackathon.police_community_app.backend.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SmsCodeRepository smsCodeRepository;

    private final SmsService smsService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public void requestCode(String phoneNumber) {
//        String code = String.format("%06d", new Random().nextInt(999999));
        String code = "999999";

        Optional<SmsCode> smsCode = smsCodeRepository.findByPhoneNumberAndCodeOptional(phoneNumber, code);

        if (smsCode.isEmpty()) {
            SmsCode newSmsCode = new SmsCode();
            newSmsCode.setPhoneNumber(phoneNumber)
                    .setCode(code)
                    .setExpiresAt(LocalDateTime.now().plusMinutes(5));

            smsCodeRepository.save(newSmsCode);
        } else {
            smsCode.get().setExpiresAt(LocalDateTime.now().plusMinutes(5));
        }
        // @TODO: Отправку СМС
//        smsService.sendSms(phoneNumber, code);
    }

    public String verifyCode(String phoneNumber, String code) {
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber, code);

        SmsCodeAuthenticationToken authResult = (SmsCodeAuthenticationToken) authenticationManager.authenticate(authRequest);

        SecurityContextHolder.getContext().setAuthentication(authResult);

        Long userId = (Long) authResult.getPrincipal();
        String role = authResult.getAuthorities().iterator().next().getAuthority();
        return jwtUtil.generateToken(userId, phoneNumber, Role.valueOf(role));
    }
}
