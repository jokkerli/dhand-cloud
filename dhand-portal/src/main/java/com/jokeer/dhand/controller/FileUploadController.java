package com.jokeer.dhand.controller;

import com.jokeer.dhand.annoations.UserLoginToken;
import com.jokeer.dhand.bean.Result;
import com.jokeer.dhand.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/file")
@Tag(name = "文件上传控制层")
public class FileUploadController {

    @Autowired
    private FileService fileService;
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket-name}")
    private String bucketName;
//    @PostMapping("/upload")
//    @Operation(summary = "文件上传服务")
//    @UserLoginToken
//    public Result uploadFile(@RequestParam("file") MultipartFile file){
//        String filename = file.getOriginalFilename();
//
//        String filePath  = uploadPath + "/"+ filename;
//
//        try {
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.fail("上传失败");
//        }
//
//        return Result.ok(filePath);
//    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file,bucketName);
    }



}
