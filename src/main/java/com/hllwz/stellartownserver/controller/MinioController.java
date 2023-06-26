package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import static com.hllwz.stellartownserver.common.ResultCode.UPLOAD_ERROR;
import static com.hllwz.stellartownserver.common.ResultCode.UPLOAD_SUCCESS;

/**
 * Minio控制器
 * @author Linorman
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/minio")
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String bucketName = "your-bucket-name";
                String objectName = file.getOriginalFilename();
                minioService.uploadFile(bucketName, objectName, file.getInputStream());
                return ResponseResult.success(UPLOAD_SUCCESS, null);
            } else {
                return ResponseResult.error(UPLOAD_ERROR, null);
            }
        } catch (Exception e) {
            return ResponseResult.error(UPLOAD_ERROR, null);
        }
    }

    /**
     * 下载文件
     * @param fileName
     * @return
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            String bucketName = "stellar-town-app";
            InputStream inputStream = minioService.downloadFile(bucketName, fileName);
            if (inputStream != null) {
                ByteArrayResource resource = new ByteArrayResource(inputStream.readAllBytes());
                return ResponseEntity.ok()
                        .header("Content-Disposition", "attachment; filename=" + fileName)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



