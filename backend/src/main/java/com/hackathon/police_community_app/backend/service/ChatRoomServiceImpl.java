package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.ChatNotificationDto;
import com.hackathon.police_community_app.backend.entity.ChatMessage;
import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.MessageStatus;
import com.hackathon.police_community_app.backend.mapper.ChatMessageMapper;
import com.hackathon.police_community_app.backend.repository.ChatMessageRepository;
import com.hackathon.police_community_app.backend.repository.ChatRoomRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public ChatRoom getChatOrCreateAnonymous(Long senderId, Long recipientId, User sender, User recipient) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findBySenderAndRecipientOptional(senderId, recipientId);

        if (chatRoom.isEmpty()) {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setSender(sender)
                    .setRecipient(recipient);

            chatRoomRepository.save(newChatRoom);
            return newChatRoom;
        }

        return chatRoom.get();
    }

    @Override
    public void assignUser(@NotNull Long userId, @NotNull Long chatRoomId) {
        User user = userRepository.findByIdRequired(userId);
        ChatRoom chatRoom = chatRoomRepository.findByIdRequired(chatRoomId);

        chatRoom.setRecipient(user);

        ChatMessage systemMessage = new ChatMessage();
        systemMessage.setContent("Пользователь " + user.getUsername() + " назначен ответственным")
                .setSender(user)
                .setAssignedTo(chatRoom.getSender())
                .setAssigned(true)
                .setChatRoom(chatRoom)
                .setStatus(MessageStatus.ASSIGNED);

        chatMessageRepository.save(systemMessage);

        if (Objects.nonNull(chatRoom.getSender())) {
            simpMessagingTemplate.convertAndSend(
                    "/queue/messages/" + chatRoom.getSender().getId(),
                    chatMessageMapper.toResponse(systemMessage)
            );
        }
    }
}
