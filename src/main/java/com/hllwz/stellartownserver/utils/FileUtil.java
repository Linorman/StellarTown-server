package com.hllwz.stellartownserver.utils;

import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * minio继承工具类
 *
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
public class FileUtil extends MinioClient {

    public FileUtil(String api, String accesskey, String secretkey) {
        this(MinioClient.builder()
                .endpoint(api)
                .credentials(accesskey, secretkey)
                .build());
    }

    private FileUtil(MinioClient client) {
        super(client);
    }

    /**
     * 判断存储桶是否存在
     *
     * @param bucket
     * @return
     */
    public Boolean bucketExists(String bucket) {
        try {
            return this.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("判断存储桶是否存在失败");
        }
        return false;
    }

    /**
     * 创建存储桶
     *
     * @param bucket
     * @return
     */
    public Boolean makeBucket(String bucket) {
        try {
            this.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            return true;
        } catch (Exception e) {
            log.error("创建存储桶失败");
        }
        return false;
    }

    /**
     * 文件上传
     * @param filePath
     * @param inputStream
     * @return Boolean
     */
    public Boolean upload(String filePath, InputStream inputStream) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket("stellar-town")
                    .object(filePath)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            this.putObject(objectArgs);
        } catch (Exception e) {
            log.error("文件上传失败,错误信息:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 预览图片
     * @param fileName
     * @return
     */
    public String preview(String fileName){
        new GetPresignedObjectUrlArgs();
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder().bucket("stellar-town").object(fileName).method(Method.GET).build();
        try {
            return this.getPresignedObjectUrl(build);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化方法
     * @return
     */
    @PostConstruct
    private void init() {
        if (!this.bucketExists("stellar-town")) {
            if (this.makeBucket("stellar-town")) {
                log.info("创建存储桶成功");
            } else {
                log.error("创建存储桶失败");
            }
        }
    }
}
