package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public Optional<User> getByIdOptional(Long id) {
        return userRepository.findByIdOptional(id);
    }

    public User getByIdRequired(Long id) {
        return userRepository.findByIdRequired(id);
    }
}
