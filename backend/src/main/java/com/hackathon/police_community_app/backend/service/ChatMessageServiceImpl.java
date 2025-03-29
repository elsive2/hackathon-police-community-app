package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.ChatMessageRequest;
import com.hackathon.police_community_app.backend.entity.ChatMessage;
import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.MessageStatus;
import com.hackathon.police_community_app.backend.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage save(ChatMessageRequest chatMessageDto, ChatRoom chatRoom, User sender, User recipient) {
        ChatMessage message = new ChatMessage();
        message.setChatRoom(chatRoom)
                .setSender(sender)
                .setContent(chatMessageDto.getContent())
                .setStatus(MessageStatus.PENDING)
                .setAssigned(false);

        if (recipient != null) {
            message.setAssignedTo(recipient)
                    .setAssigned(true)
                    .setStatus(MessageStatus.ASSIGNED);
        }

        chatMessageRepository.save(message);

        return message;
    }
}
