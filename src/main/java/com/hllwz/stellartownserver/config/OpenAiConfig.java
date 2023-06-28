package com.hllwz.stellartownserver.config;

import com.hllwz.stellartownserver.utils.OpenAiUtil;
import com.theokanning.openai.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * OpenAi配置类
 * @author Linorman
 * @version 1.0.0
 */
@Configuration
public class OpenAiConfig {
    @Bean
    public OpenAiUtil openAiUtil() {
        return new OpenAiUtil(new OkHttpClient().newBuilder()
                .callTimeout(Duration.ofSeconds(10))
                .build());
    }
}
