package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;


/**
 * openai服务类
 * @author Linorman
 * @version 1.0.0
 */
public interface OpenAiService {
    /**
     * 修改图片接口
     * @param  file 图片文件
     * @return ResponseResult
     */
    public ResponseResult changeImage(MultipartFile file, String prompt);
}
