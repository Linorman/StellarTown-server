package com.hllwz.stellartownserver.config;

import com.hllwz.stellartownserver.utils.FileUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;


/**
 * Minio配置类
 * @author Linorman
 * @version 1.0.0
 */
@Configuration
public class MinioConfig {

    @Bean
    @ConfigurationProperties(prefix = "minio")
    public Properties getProperties(){
        return new Properties();
    }

    @Bean
    public FileUtil getFileUtil(){
        Properties properties = getProperties();
        FileUtil fileUtil = new FileUtil(properties.getEndpoint(), properties.getAccesskey(), properties.getSecretkey());

        return fileUtil;
    }

    @Data
    private static class Properties {
        private String endpoint;
        private String accesskey;
        private String secretkey;
    }
}
