package com.hackathon.police_community_app.backend.dto.response;

import com.hackathon.police_community_app.backend.entity.QueryPhoto;
import com.hackathon.police_community_app.backend.enums.QueryCategory;
import com.hackathon.police_community_app.backend.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QueryResponse {
    private Long id;
    private String content;
    private String title;
    private Status status;
    private QueryCategory category;
    private List<QueryPhotoResponse> photos;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private RightsResponse rightsResponse;
}
