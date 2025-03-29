package com.hackathon.police_community_app.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {
    private String storedFilename;
    private String originalFilename;
    private String downloadUrl;
    private Long size;
    private String contentType;
}
