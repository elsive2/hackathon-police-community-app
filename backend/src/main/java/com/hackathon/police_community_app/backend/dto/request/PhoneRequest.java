package com.hackathon.police_community_app.backend.dto.request;

import com.hackathon.police_community_app.backend.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotBlank(message = "Номер телефона не может быть пустым")
    @PhoneNumber
    private String phone;
}
