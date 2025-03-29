package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.repository.ChatRoomRepository;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    private final UserRepository userRepository;

    @Override
    public ChatRoom getChatOrCreateAnonymous(Long senderId, Long recipientId, User sender, User recipient) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findBySenderAndRecipientOptional(senderId, recipientId);

        if (chatRoom.isEmpty()) {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setSender(sender)
                    .setRecipient(recipient);
            return newChatRoom;
        }

        return chatRoom.get();
    }
}
