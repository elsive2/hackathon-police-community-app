package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.ChatMessageResponse;
import com.hackathon.police_community_app.backend.entity.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ChatMessageMapper {
    private final UserMapper userMapper;

    public ChatMessageResponse toResponse(ChatMessage chatMessage) {
        ChatMessageResponse response = new ChatMessageResponse();

        response.setId(chatMessage.getId())
                .setChatRoomId(chatMessage.getChatRoom().getId())
                .setContent(chatMessage.getContent())
                .setSender(userMapper.toResponse(chatMessage.getSender()))
                .setRecipient(chatMessage.getAssignedTo() != null ? userMapper.toResponse(chatMessage.getAssignedTo()) : null)
                .setAssigned(chatMessage.isAssigned())
                .setCreateDate(chatMessage.getCreateDate());
        return response;
    }
}
