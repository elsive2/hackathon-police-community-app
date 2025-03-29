package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.ChatMessageRequest;
import com.hackathon.police_community_app.backend.entity.ChatMessage;
import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;

public interface ChatMessageService {
    ChatMessage save(ChatMessageRequest chatMessage, ChatRoom chat, User sender, User recipient);
}
