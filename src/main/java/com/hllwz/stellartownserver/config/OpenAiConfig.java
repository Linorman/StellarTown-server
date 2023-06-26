package com.hllwz.stellartownserver.config;

import com.theokanning.openai.OpenAiService;
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

    private String openAiKey;

    private long timeout;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(openAiKey, Duration.ofSeconds(timeout));
    }
}
