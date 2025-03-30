package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.dto.response.TokenResponse;
import com.hackathon.police_community_app.backend.entity.RefreshToken;
import com.hackathon.police_community_app.backend.entity.SmsCode;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.exception.InvalidRefreshTokenException;
import com.hackathon.police_community_app.backend.exception.RefreshTokenExpiredException;
import com.hackathon.police_community_app.backend.repository.RefreshTokenRepository;
import com.hackathon.police_community_app.backend.repository.SmsCodeRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import com.hackathon.police_community_app.backend.util.JwtUtil;
import com.hackathon.police_community_app.backend.util.StringUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public void requestCode(String phoneNumber) {
//        String code = String.format("%06d", new Random().nextInt(999999));
        String code = "999999";
        phoneNumber = StringUtil.formatPhoneNumber(phoneNumber);

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
        // @TODO: Ограничение СМС если много
//        smsService.sendSms(phoneNumber, code);
    }

    public TokenResponse verifyCode(String phoneNumber, String code) {
        phoneNumber = StringUtil.formatPhoneNumber(phoneNumber);
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber, code);

        SmsCodeAuthenticationToken authResult = (SmsCodeAuthenticationToken) authenticationManager.authenticate(authRequest);

        SecurityContextHolder.getContext().setAuthentication(authResult);

        Long userId = (Long) authResult.getPrincipal();
        String role = authResult.getAuthorities().iterator().next().getAuthority();

        User user = userRepository.findByIdRequired(userId);

        return generateTokenResponse(user);
    }

    public TokenResponse verifyRefreshToken(String rawToken) {
        String encodedToken = encoder.encode(rawToken);

        RefreshToken storedToken = refreshTokenRepository.findByTokenOptional(encodedToken)
                .orElseThrow(RuntimeException::new);

        if (!encoder.matches(rawToken, storedToken.getToken())) {
            throw new InvalidRefreshTokenException();
        }

        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(storedToken);
            throw new RefreshTokenExpiredException();
        }

        refreshTokenRepository.delete(storedToken);

        return generateTokenResponse(storedToken.getUser());

    }

    private void createRefreshToken(User user, String token) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user)
                .setToken(encoder.encode(token))
                .setExpiryDate(LocalDateTime.now().plusSeconds(Long.parseLong(jwtUtil.getRefreshExpirationTime())));

        refreshTokenRepository.save(refreshToken);
    }

    private @NotNull TokenResponse generateTokenResponse(User user) {
        String refreshToken = jwtUtil.generateRefreshToken(user.getId(), user.getPhoneNumber(), user.getRole());
        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getPhoneNumber(), user.getRole());

        createRefreshToken(user, refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
