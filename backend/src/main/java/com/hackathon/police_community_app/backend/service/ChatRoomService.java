package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.entity.ChatRoom;
import com.hackathon.police_community_app.backend.entity.User;
import jakarta.validation.constraints.NotNull;

public interface ChatRoomService {
    ChatRoom getChatOrCreateAnonymous(Long senderId, Long recipientId, User sender, User recipient);

    SingleMessageResponse assignUser(@NotNull Long userId, @NotNull Long chatRoomId);
}
