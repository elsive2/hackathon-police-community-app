package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.exception.UserNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    default Optional<User> findByPhoneNumberOptional(String phoneNumber) {
        return findByPhoneNumber(phoneNumber);
    }

    default User findByPhoneNumberRequired(String phoneNumber) {
        return findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
    }
}
