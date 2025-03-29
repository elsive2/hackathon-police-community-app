package com.hackathon.police_community_app.backend.dto.response.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextQueryCategoryResponse {
    private String name;
    private List<QueryCategoryResponse> values;

    @Data
    @AllArgsConstructor
    public static class QueryCategoryResponse {
        private String name;
        private String displayName;
        private Integer priority;
    }
}
