package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;

import java.util.Optional;

public interface ChatRoomService {
    ChatRoom getChatOrCreateAnonymous(Long senderId, Long recipientId, User sender, User recipient);
}
