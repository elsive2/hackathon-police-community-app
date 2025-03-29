package com.hackathon.police_community_app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatNotificationDto {
    private Long id;
    private Long senderId;
    private String senderName;
}
