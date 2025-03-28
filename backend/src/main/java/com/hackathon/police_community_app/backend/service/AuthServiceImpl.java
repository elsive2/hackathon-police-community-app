package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.entity.SmsCode;
import com.hackathon.police_community_app.backend.enums.Role;
import com.hackathon.police_community_app.backend.repository.SmsCodeRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import com.hackathon.police_community_app.backend.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SmsCodeRepository smsCodeRepository;

    private final UserRepository userRepository;

    private final SmsService smsService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public void requestCode(String phoneNumber) {
//        String code = String.format("%06d", new Random().nextInt(999999));
        String code = "999999";
        SmsCode smsCode = new SmsCode();
        smsCode.setPhoneNumber(phoneNumber)
                .setCode(code)
                .setExpiresAt(LocalDateTime.now().plusMinutes(5));

        smsCodeRepository.save(smsCode);

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

    @Override
    public UserDetails getByPhone(String phone) {
        return userRepository.findByPhoneNumberRequired(phone);
    }

//    public String loginPolice(String phoneNumber, String password) {
//        User user = userRepository.findByPhoneNumber(phoneNumber)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!user.getRole().equals(Role.ROLE_POLICE)) {
//            throw new RuntimeException("User is not a police officer");
//        }
//
//        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        // Генерируем JWT-токен
//        return jwtUtil.generateToken(user.getId(), user.getRole().name());
//    }
}
