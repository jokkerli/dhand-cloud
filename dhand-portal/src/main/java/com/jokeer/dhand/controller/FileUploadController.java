package com.jokeer.dhand.controller;

import com.jokeer.dhand.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Api("文件上传")
public class FileUploadController {



    @Value("${file.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
//    @UserLoginToken
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();

        String filePath  = uploadPath + "/"+ filename;

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败");
        }

        return Result.ok(filePath);
    }



}
