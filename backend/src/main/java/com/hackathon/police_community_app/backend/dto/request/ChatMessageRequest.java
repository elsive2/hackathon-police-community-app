package com.hackathon.police_community_app.backend.dto.request;

import com.hackathon.police_community_app.backend.enums.MessageStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageRequest {
    @NotBlank(message = "Идентификатор чата не может быть пустым")
    @Size(min = 1, max = 50, message = "Идентификатор чата должен содержать от 1 до 50 символов")
    private String chatId;

    @NotNull(message = "Идентификатор отправителя не может быть null")
    @Positive(message = "Идентификатор отправителя должен быть положительным числом")
    private Long senderId;

    @NotNull(message = "Идентификатор получателя не может быть null")
    @Positive(message = "Идентификатор получателя должен быть положительным числом")
    private Long recipientId;

    @NotBlank(message = "Имя отправителя не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя отправителя должно содержать от 1 до 100 символов")
    private String senderName;

    @NotBlank(message = "Имя получателя не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя получателя должно содержать от 1 до 100 символов")
    private String recipientName;

    @NotBlank(message = "Содержимое сообщения не может быть пустым")
    @Size(min = 1, max = 2000, message = "Содержимое сообщения должно содержать от 1 до 2000 символов")
    private String content;

    @NotNull(message = "Временная метка не может быть null")
    @PastOrPresent(message = "Временная метка должна быть в прошлом или настоящем")
    private LocalDateTime timestamp;

    @NotNull(message = "Статус сообщения не может быть null")
    private MessageStatus status;
}
