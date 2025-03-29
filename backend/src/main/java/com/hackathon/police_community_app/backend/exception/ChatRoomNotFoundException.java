package com.hackathon.police_community_app.backend.exception;

import com.hackathon.police_community_app.backend.entity.BaseEntity;
import org.springframework.http.HttpStatus;

public class ChatRoomNotFoundException extends BaseException {
    public ChatRoomNotFoundException() {
        super("Чат не найден", HttpStatus.NOT_FOUND);
    }
}
