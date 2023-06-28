package com.hllwz.stellartownserver.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * OpenAi工具类
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class OpenAiUtil {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final String key = "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";
    private final OkHttpClient client;

    public String transferImage(File file, String prompt, int n, String size) throws IOException {
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "otter.png",
                        RequestBody.create(file, MEDIA_TYPE_PNG))
                .addFormDataPart("prompt", prompt)
                .addFormDataPart("n", String.valueOf(n))
                .addFormDataPart("size", size);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/images/edits")
                .addHeader("Authorization", "Bearer " + key)
                .post(requestBodyBuilder.build())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            // 处理响应结果
            String responseBody = null;
            if (response.body() != null) {
                responseBody = response.body().string();
            }
            System.out.println(responseBody);


            JSONObject responseJson = JSONObject.parseObject(responseBody);
            JSONArray data = responseJson.getJSONArray("data");
            if (data.size() > 0) {
                JSONObject firstObject = data.getJSONObject(0);
                return firstObject.getString("url");
            } else {
                return null;
            }
        }
    }

}
