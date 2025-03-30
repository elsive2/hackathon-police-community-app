package com.hackathon.police_community_app.backend.dto.request;

import com.hackathon.police_community_app.backend.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCodeRequest {
    @PhoneNumber
    private String phone;

    @NotBlank(message = "Код не может быть пустым")
    private String code;
}
