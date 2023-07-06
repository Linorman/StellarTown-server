package com.hllwz.stellartownserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 一键换天工具类
 * @auther Linorman
 * @version 1.0.0
 */
@Slf4j
@Service
public class SkyTransferService {

    private final String url = "http://i-2.gpushare.com:28927/api/sky-transfer";

    public ResponseEntity transfer(MultipartFile file, Integer maskId) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("file", file.getOriginalFilename(),
                                    RequestBody.create(file.getBytes()))
                            .addFormDataPart("maskId", String.valueOf(maskId))
                            .build())
                    .build();
        } catch (Exception e) {
            log.error("构建请求失败");
            return ResponseEntity.badRequest().build();
        }

        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            // 从响应中获取图像流字节数组
            InputStream inputStream;
            if (response.body() != null) {
                inputStream = response.body().byteStream();
            }

            // 设置响应头，指定图像类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.asMediaType(MimeType.valueOf("image/jpeg")));  // 可根据实际图像类型进行调整

            return new ResponseEntity<>(response.body(), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("请求失败");
            return ResponseEntity.badRequest().build();
        }

    }
}
