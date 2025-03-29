package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.ArticleRequest;
import com.hackathon.police_community_app.backend.dto.response.ArticleResponse;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;

public interface ArticleService {
    PagedResponse<ArticleResponse> getAll(int page, int size);

    PagedResponse<ArticleResponse> getByAuthorId(Long authorId, int page, int size);

    ArticleResponse getById(Long id);

    ArticleResponse create(ArticleRequest request, Long authorId);

    ArticleResponse update(Long id, ArticleRequest request);

    void delete(Long id);
}
