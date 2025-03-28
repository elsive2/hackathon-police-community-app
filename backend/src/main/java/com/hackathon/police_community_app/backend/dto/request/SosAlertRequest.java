package com.hackathon.police_community_app.backend.dto.request;

import lombok.Data;

@Data
public class SosAlertRequest {
    private Double latitude;

    private Double longitude;

    private String phoneNumber;
}
