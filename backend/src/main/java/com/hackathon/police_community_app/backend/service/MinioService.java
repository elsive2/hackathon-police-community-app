package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.response.FileUploadResponse;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService implements UploadService {
    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.public-url}")
    private String publicUrl;

    public FileUploadResponse uploadFile(MultipartFile file) {
        try {
            validateFile(file);

            String originalFilename = file.getOriginalFilename();
            String fileExtension = StringUtils.getFilenameExtension(originalFilename);
            String generatedFilename = UUID.randomUUID() + "." + fileExtension;

            ensurePublicBucketExists();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(generatedFilename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            String directUrl = String.format("%s/%s/%s", publicUrl, bucketName, generatedFilename);

            return new FileUploadResponse(
                    generatedFilename,
                    originalFilename,
                    directUrl,
                    file.getSize(),
                    file.getContentType()
            );
        } catch (Exception e) {
            log.error("File upload failed", e);
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    private void ensurePublicBucketExists() throws Exception {
        boolean bucketExists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build());

        if (!bucketExists) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());

            // Устанавливаем публичную политику доступа
            String publicPolicy = """
                {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Effect": "Allow",
                            "Principal": "*",
                            "Action": ["s3:GetObject"],
                            "Resource": ["arn:aws:s3:::%s/*"]
                        }
                    ]
                }
                """.formatted(bucketName);

            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(publicPolicy)
                            .build());
        }
    }
}