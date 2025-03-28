package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.request.PhoneRequest;
import com.hackathon.police_community_app.backend.dto.request.VerifyCodeRequest;
import com.hackathon.police_community_app.backend.dto.response.AuthResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @TODO: Придумать как сделать версионность
@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/request-code")
    public SingleMessageResponse requestCode(@RequestBody PhoneRequest request) {
        authService.requestCode(request.getPhone());
        return new SingleMessageResponse("Code has been sent");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<AuthResponse> verifyCode(@RequestBody VerifyCodeRequest request) {
        String token = authService.verifyCode(request.getPhone(), request.getCode());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
