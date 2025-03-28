package com.hackathon.police_community_app.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCodeRequest {
    private String phone;
    private String code;
}
