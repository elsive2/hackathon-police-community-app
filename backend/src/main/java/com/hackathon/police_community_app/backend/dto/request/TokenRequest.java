package com.hackathon.police_community_app.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequest {

    @NotBlank(message = "Токен не может быть пустым")
    private String token;
}
