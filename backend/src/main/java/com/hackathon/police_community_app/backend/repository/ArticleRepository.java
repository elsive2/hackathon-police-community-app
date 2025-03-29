package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.Article;
import com.hackathon.police_community_app.backend.entity.SosAlert;
import com.hackathon.police_community_app.backend.exception.ArticleNotFoundException;
import com.hackathon.police_community_app.backend.exception.SosAlertNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Page<Article> findByIsDeletedFalseOrderByCreateDateDesc(Pageable pageable);

    default Page<Article> findAll(Pageable pageable) {
        return findByIsDeletedFalseOrderByCreateDateDesc(pageable);
    }

    Page<Article> findByAuthorIdAndIsDeletedFalseOrderByCreateDateDesc(Long authorId, Pageable pageable);

    default Page<Article> findByAuthor(Long authorId, Pageable pageable) {
        return findByAuthorIdAndIsDeletedFalseOrderByCreateDateDesc(authorId, pageable);
    }

    Optional<Article> findByIdAndIsDeletedFalse(Long id);

    default Article findByIdRequired(Long id) {
        return findByIdAndIsDeletedFalse(id).orElseThrow(ArticleNotFoundException::new);
    }
}
