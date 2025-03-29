package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.Query;
import com.hackathon.police_community_app.backend.exception.QueryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryRepository extends CrudRepository<Query, Long> {
    Page<Query> findByIsDeletedFalseOrderByCreateDateDesc(Pageable pageable);

    default Page<Query> findAll(Pageable pageable) {
        return findByIsDeletedFalseOrderByCreateDateDesc(pageable);
    }

    Page<Query> findByUserIdAndIsDeletedFalseOrderByCreateDateDesc(Long userId, Pageable pageable);

    default Page<Query> findByUser(Long userId, Pageable pageable) {
        return findByUserIdAndIsDeletedFalseOrderByCreateDateDesc(userId, pageable);
    }

    Optional<Query> findByIdAndIsDeletedFalse(Long id);

    default Query findByIdRequired(Long id) {
        return findByIdAndIsDeletedFalse(id).orElseThrow(QueryNotFoundException::new);
    }
}
