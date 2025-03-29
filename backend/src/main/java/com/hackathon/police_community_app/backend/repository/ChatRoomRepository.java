package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId, Long recipientId);

    default Optional<ChatRoom> findBySenderAndRecipientOptional(Long senderId, Long recipientId) {
        return findBySenderIdAndRecipientId(recipientId, senderId);
    }

    default ChatRoom findBySenderAndRecipientRequired(Long senderId, Long recipientId) {
        return findBySenderIdAndRecipientId(recipientId, senderId).orElseThrow();
    }
}
