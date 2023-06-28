package com.hllwz.stellartownserver.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * OpenAi工具类
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class OpenAiUtil {

    private final String key;
    private final String model;

    public String transferImage(String imageUrl) {
        String postUrl = "https://api.openai.com/v1/images/edits";

        return null;
    }

}
