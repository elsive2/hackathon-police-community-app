package com.hackathon.police_community_app.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponse {
    private Long id;
    private Long chatRoomId;
    private String content;
    private UserResponse sender;
    private UserResponse recipient;
    private LocalDateTime createDate;
    private boolean isAssigned;

}
