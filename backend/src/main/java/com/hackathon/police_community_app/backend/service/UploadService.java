package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    FileUploadResponse uploadFile(MultipartFile file);
}
