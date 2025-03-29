package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByIdOptional(Long id);

    User getByIdRequired(Long id);
}
