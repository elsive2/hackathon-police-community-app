package com.hackathon.police_community_app.backend.dto.response;

import com.hackathon.police_community_app.backend.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SosAlertResponse {
    private Long id;

    private Double latitude;

    private Double longitude;

    private String phoneNumber;

    private Status status;

    private String comment;

    private LocalDateTime createDate;

    private LocalDateTime changeDate;

    private RightsResponse rightsResponse;
}
