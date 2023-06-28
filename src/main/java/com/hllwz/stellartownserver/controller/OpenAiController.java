package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * OpenAi控制器类
 * @author Linorman
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
public class OpenAiController {

    public final OpenAiService openAiService;

    /**
     * 修改图片接口
     * @param  file 图片文件
     * @return ResponseResult
     */
    @PostMapping("/changeImage")
    public ResponseResult changeImage(@RequestParam("image") MultipartFile file, @RequestParam("prompt") String prompt) {
        return openAiService.changeImage(file, prompt);
    }

}
