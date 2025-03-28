package com.hackathon.police_community_app.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SosAlertResponse {
    private Long id;

    private Double latitude;

    private Double longitude;

    private String phoneNumber;

    private LocalDateTime createDate;

    private LocalDateTime changeDate;
}
