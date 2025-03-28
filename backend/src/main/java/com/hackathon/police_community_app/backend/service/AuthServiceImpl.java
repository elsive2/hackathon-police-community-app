package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.entity.SmsCode;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Role;
import com.hackathon.police_community_app.backend.exception.SmsCodeExpiredException;
import com.hackathon.police_community_app.backend.exception.InvalidSmsCodeException;
import com.hackathon.police_community_app.backend.repository.SmsCodeRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import com.hackathon.police_community_app.backend.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SmsCodeRepository smsCodeRepository;

    private final UserRepository userRepository;

    private final SmsService smsService;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public void requestCode(String phoneNumber) {
        // Генерируем код
        String code = String.format("%06d", new Random().nextInt(999999));
        SmsCode smsCode = new SmsCode();
        smsCode.setPhoneNumber(phoneNumber);
        smsCode.setCode(code);
        smsCode.setCreatedAt(LocalDateTime.now());
        smsCode.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        smsCodeRepository.save(smsCode);

        // Отправляем SMS
        smsService.sendSms(phoneNumber, code);
    }

    public String verifyCode(String phoneNumber, String code) {
        // Создаем токен для аутентификации
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber, code);

        // Аутентифицируем через AuthenticationManager
        SmsCodeAuthenticationToken authResult = (SmsCodeAuthenticationToken) authenticationManager.authenticate(authRequest);

        // Добавляем пользователя в SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // Генерируем JWT-токен
        Long userId = (Long) authResult.getPrincipal();
        String role = authResult.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        return jwtTokenProvider.generateToken(userId, role);
    }

    @Override
    public UserDetails getByUsername(String username) {
        return userRepository.findByUsernameRequired(username);
    }

    public String loginPolice(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.ROLE_POLICE)) {
            throw new RuntimeException("User is not a police officer");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        // Генерируем JWT-токен
        return jwtUtil.generateToken(user.getId(), user.getRole().name());
    }
}
