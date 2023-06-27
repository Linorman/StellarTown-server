package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
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
     * 上传用户头像
     * @param file
     * @return
     */
    @PostMapping("/upload/avatar")
    public ResponseResult uploadFile(@RequestParam("avatar") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String objectName = file.getOriginalFilename();
                return minioService.uploadAvatar(objectName, file.getInputStream());
            } else {
                return ResponseResult.error(ResultCode.AVATAR_UPLOAD_ERROR, null);
            }
        } catch (Exception e) {
            return ResponseResult.error(ResultCode.AVATAR_UPLOAD_ERROR, null);
        }
    }
    /**
     * 上传帖子图片
     * @param file
     * @return
     */
    @PostMapping("/upload/post")
    public ResponseResult uploadPost(@RequestParam("post") MultipartFile file, @RequestParam("postId") Integer postId) {
        try {
            if (!file.isEmpty()) {
                String objectName = file.getOriginalFilename();
                return minioService.uploadPost(objectName, file.getInputStream(), postId);
            } else {
                return ResponseResult.error(ResultCode.POST_UPLOAD_ERROR, null);
            }
        } catch (Exception e) {
            return ResponseResult.error(ResultCode.POST_UPLOAD_ERROR, null);
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



