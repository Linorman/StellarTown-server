package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.common.ResponseResult;

import java.io.InputStream;

/**
 * Minio服务接口
 * @author Linorman
 * @version 1.0.0
 */
public interface MinioService {

    /**
     * 上传文件
     * @param bucketName
     * @param objectName
     * @param stream
     */
    public ResponseResult uploadAvatar(String objectName, InputStream stream);

    /**
     * 下载文件
     * @param bucketName
     * @param objectName
     * @return
     */
    public InputStream downloadFile(String bucketName, String objectName);

    /**
     * 上传帖子图片
     * @param objectName
     * @param inputStream
     * @return
     */
    ResponseResult uploadPost(String objectName, InputStream inputStream, Integer postId);
}
