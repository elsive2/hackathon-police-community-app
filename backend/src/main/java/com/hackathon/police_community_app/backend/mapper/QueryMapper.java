package com.hackathon.police_community_app.backend.mapper;

import com.hackathon.police_community_app.backend.dto.response.QueryPhotoResponse;
import com.hackathon.police_community_app.backend.dto.response.QueryResponse;
import com.hackathon.police_community_app.backend.dto.response.RightsResponse;
import com.hackathon.police_community_app.backend.entity.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Component
public class QueryMapper {
    private final QueryPhotoMapper queryPhotoMapper;

    public QueryResponse toResponse(Query query) {
        List<QueryPhotoResponse> photos = query.getPhotos().stream().map(queryPhotoMapper::toResponse).toList();

        QueryResponse response = new QueryResponse();
        response.setId(query.getId())
                .setContent(query.getContent())
                .setTitle(query.getTitle())
                .setStatus(query.getStatus())
                .setCategory(query.getCategory())
                .setPhotos(photos)
                .setCreateDate(query.getCreateDate())
                .setChangeDate(query.getChangeDate())
                .setRightsResponse(
                        (new RightsResponse())
                                .setOccupied(Objects.nonNull(query.getResponsible()))
                );

        return response;
    }
}
