package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.service.MinioService;
import io.minio.*;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio服务实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    @Override
    public void uploadFile(String bucketName, String objectName, InputStream inputStream) {
        try {
            ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .build());
            // 文件上传成功
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            // 处理异常

        }
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            // return minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(objectName).build()));
        } catch (MinioException e) {
            // 处理异常
            return null;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
