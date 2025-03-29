package com.hackathon.police_community_app.backend.handler.ws;

import com.hackathon.police_community_app.backend.dto.ChatNotificationDto;
import com.hackathon.police_community_app.backend.dto.request.ChatMessageRequest;
import com.hackathon.police_community_app.backend.entity.ChatMessage;
import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.service.ChatMessageService;
import com.hackathon.police_community_app.backend.service.ChatRoomService;
import com.hackathon.police_community_app.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @SneakyThrows
    @SendTo("/topic/messaging")
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageRequest chatMessage, Principal principal) {
        Thread.sleep(3000L);

        System.out.println("dsdasd");

        User sender = userService.getByIdRequired(chatMessage.getSenderId());
        User recipient = userService.getByIdOptional(chatMessage.getRecipientId()).orElse(null);

        ChatRoom chat = chatRoomService.getChatOrCreateAnonymous(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                sender,
                recipient
        );

        ChatMessage message = chatMessageService.save(chatMessage, chat, sender, recipient);

        if (message.getAssignedTo() != null) {
            simpMessagingTemplate.convertAndSendToUser(
                    chatMessage.getRecipientId().toString(),
                    "/queue/messages",
                    new ChatNotificationDto(
                            message.getId(),
                            message.getAssignedTo().getId(),
                            message.getAssignedTo().getPhoneNumber()
                    )
            );
        }
    }
}
