package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.service.OpenAiService;
import com.hllwz.stellartownserver.utils.OpenAiUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * openai服务实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiServiceImpl implements OpenAiService {

    public final OpenAiUtil openAiUtil;
    @Override
    public ResponseResult changeImage(MultipartFile file , String prompt) {
        File pngFile;
        try {
            pngFile = File.createTempFile("converted", ".png");
        } catch (IOException e) {
            log.error("文件转换失败", e);
            return ResponseResult.error(ResultCode.IMAGE_NOT_PNG, null);
        }

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(pngFile)) {
            // 将输入流中的数据复制到输出流中
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            log.error("文件转换失败", e);
            return ResponseResult.error(ResultCode.IMAGE_NOT_PNG, null);
        }
        BufferedImage image;
        try {
            image = ImageIO.read(pngFile);
        } catch (IOException e) {
            log.error("文件转换失败", e);
            return ResponseResult.error(ResultCode.IMAGE_NOT_PNG, null);
        }

        int width = image.getWidth();
        int height = image.getHeight();
        String size = width + "x" + height;
        try {
            String url = openAiUtil.transferImage(pngFile, prompt, 1, size);
            return ResponseResult.success(ResultCode.IMAGE_MAKE_SUCCESS, url);
        } catch (Exception e) {
            log.error("文件生成失败", e);
            return ResponseResult.error(ResultCode.IMAGE_MAKE_ERROR, null);
        }
    }
}
