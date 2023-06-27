package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.service.MinioService;
import com.hllwz.stellartownserver.utils.FileUtil;
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

    private final FileUtil minioClient;
    @Override
    public ResponseResult uploadAvatar(String objectName, InputStream inputStream) {
        boolean flag = minioClient.upload("avatar" + objectName, inputStream);
        if (flag) {
            return ResponseResult.success(ResultCode.AVATAR_UPLOAD_SUCCESS, null);
        } else {
            return ResponseResult.error(ResultCode.AVATAR_UPLOAD_ERROR, null);
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
