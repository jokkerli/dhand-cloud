package com.jokeer.dhand.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String uploadFile(MultipartFile file,String bucketName);
}
