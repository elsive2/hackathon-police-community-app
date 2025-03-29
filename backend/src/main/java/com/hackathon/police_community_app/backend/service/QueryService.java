package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.QueryRequest;
import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.QueryResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;

public interface QueryService {
    PagedResponse<QueryResponse> getAllByUser(Long userId, int page, int size);

    PagedResponse<QueryResponse> getAll(int page, int size);

    QueryResponse create(QueryRequest request, Long userId);

    QueryResponse update(Long id, QueryRequest request);

    void delete(Long id);

    QueryResponse getById(Long id);

    void finish(Long id);

    void assignResponsible(Long id, Long userId);

    void reject(Long id);
}
