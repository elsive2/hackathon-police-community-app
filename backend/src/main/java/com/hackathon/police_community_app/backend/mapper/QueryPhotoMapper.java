package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.QueryPhotoResponse;
import com.hackathon.police_community_app.backend.entity.QueryPhoto;
import org.springframework.stereotype.Component;

@Component
public class QueryPhotoMapper {
    public QueryPhotoResponse toResponse(QueryPhoto queryPhoto) {
        QueryPhotoResponse response = new QueryPhotoResponse();
        response.setId(queryPhoto.getId())
                .setPhotoUrl(queryPhoto.getPhotoUrl());
        return response;
    }
}
