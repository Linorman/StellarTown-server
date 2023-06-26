package com.hllwz.stellartownserver.config;

import io.minio.MinioClient;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置类
 * @author Linorman
 * @version 1.0.0
 */
@Configuration
public class MinioConfig {

    private final String endpoint = "http://localhost:9000";
    private final String accessKey = "bCCx1vhlrGcN6TCP05iN";
    private final String secretKey = "HpNThuJgPUpD7fGpWa6eUDnYK2AdWCXNIx2ZHEPo";

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
