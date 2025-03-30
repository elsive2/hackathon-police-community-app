package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findFirstByToken(String token);

    default Optional<RefreshToken> findByTokenOptional(String rawToken) {
        return findFirstByToken(rawToken);
    }
}
