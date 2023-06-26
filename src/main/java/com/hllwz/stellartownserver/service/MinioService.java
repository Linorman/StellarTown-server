package com.hllwz.stellartownserver.service;

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
    void uploadFile(String bucketName, String objectName, InputStream stream);

    /**
     * 下载文件
     * @param bucketName
     * @param objectName
     * @return
     */
    InputStream downloadFile(String bucketName, String objectName);
}
