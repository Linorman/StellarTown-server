package com.hllwz.stellartownserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 一键换天工具类
 * @auther Linorman
 * @version 1.0.0
 */
@Slf4j
@Service
public class SkyTransferService {

    private final String url = "http://localhost:9889/api/sky-transfer";

    public ResponseEntity transfer(MultipartFile file, Integer maskId) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("file", file.getOriginalFilename(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), file.getBytes()))
                            .addFormDataPart("maskId", String.valueOf(maskId))
                            .build())
                    .build();
        } catch (Exception e) {
            log.error("构建请求失败");
            return ResponseEntity.badRequest().build();
        }

        try {
            return new OkHttpClient().newCall(request).execute().isSuccessful() ?
                    ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("请求失败");
            return ResponseEntity.badRequest().build();
        }

    }
}
