package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.UserResponse;
import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId())
                .setPhoneNumber(StringUtil.formatRawPhoneNumber(user.getPhoneNumber()))
                .setRole(user.getRole());
        return response;
    }
}
