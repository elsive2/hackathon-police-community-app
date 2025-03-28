package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.exception.UserNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    default Optional<User> findByPhoneNumberOptional(String phoneNumber) {
        return findByPhoneNumber(phoneNumber);
    }

    default UserDetails findByPhoneNumberRequired(String phoneNumber) {
        return findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
    }
}
