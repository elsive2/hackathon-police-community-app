package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.service.ChatRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "Chat", description = "Операции с чатами")
@RequestMapping("/api/chats")
@AllArgsConstructor
@RestController
public class ChatController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/assign/{chatRoomId}")
    @SendTo("/topic/messages")
    public SingleMessageResponse assign(@PathVariable Long chatRoomId, Principal principal) {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) principal;

        chatRoomService.assignUser(smsCodeAuthenticationToken.getUserId(), chatRoomId);

        return new SingleMessageResponse("Assigned");
    }
}
