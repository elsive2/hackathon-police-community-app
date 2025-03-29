package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.exception.UserNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    default Optional<User> findByPhoneNumberOptional(String phoneNumber) {
        return findByPhoneNumberAndIsDeletedFalse(phoneNumber);
    }

    default User findByPhoneNumberRequired(String phoneNumber) {
        return findByPhoneNumberAndIsDeletedFalse(phoneNumber).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findByIsDeletedFalseAndId(Long userId);

    default User findByIdRequired(Long userId) {
        return findByIsDeletedFalseAndId(userId).orElseThrow(UserNotFoundException::new);
    }

    default Optional<User> findByIdOptional(Long userId) {
        return findByIsDeletedFalseAndId(userId);
    }
}
