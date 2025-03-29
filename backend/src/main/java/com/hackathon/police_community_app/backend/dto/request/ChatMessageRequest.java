package com.hackathon.police_community_app.backend.dto.request;

import com.hackathon.police_community_app.backend.enums.MessageStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageRequest {
    private String id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private LocalDateTime timestamp;
    private MessageStatus status;
}
