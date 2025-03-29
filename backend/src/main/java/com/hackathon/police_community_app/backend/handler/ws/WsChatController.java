package com.hackathon.police_community_app.backend.handler.ws;

import com.hackathon.police_community_app.backend.dto.request.ChatMessageRequest;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
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

@AllArgsConstructor
@Controller
public class WsChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @SneakyThrows
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public SingleMessageResponse processMessage(@Payload ChatMessageRequest chatMessage) {
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

        return new SingleMessageResponse("sdasd");

//        if (recipient != null && message.getAssignedTo() != null) {
//            simpMessagingTemplate.convertAndSendToUser(
//                    chatMessage.getRecipientId().toString(),
//                    "/topic/messages",
//                    new ChatNotificationDto(
//                            message.getId(),
//                            message.getAssignedTo().getId(),
//                            message.getAssignedTo().getPhoneNumber()
//                    )
//            );
//        }
    }
}
