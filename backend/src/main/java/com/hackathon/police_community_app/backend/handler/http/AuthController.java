package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.request.PhoneRequest;
import com.hackathon.police_community_app.backend.dto.request.VerifyCodeRequest;
import com.hackathon.police_community_app.backend.dto.response.AuthResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @TODO: Придумать как сделать версионность
@Tag(name = "Auth", description = "Авторизация")
@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Запрос кода верификации по номеру телефона (мок - 999-999)")
    @PostMapping("/request-code")
    public SingleMessageResponse requestCode(@RequestBody PhoneRequest request) {
        authService.requestCode(request.getPhone());
        return new SingleMessageResponse("Code has been sent");
    }

    @Operation(summary = "Верификация кода по номеру телефона (мок - 999-999)")
    @PostMapping("/verify-code")
    public ResponseEntity<AuthResponse> verifyCode(@RequestBody VerifyCodeRequest request) {
        String token = authService.verifyCode(request.getPhone(), request.getCode());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
