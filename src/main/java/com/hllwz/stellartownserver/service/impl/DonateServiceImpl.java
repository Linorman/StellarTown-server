package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.DonateService;
import com.hllwz.stellartownserver.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hllwz.stellartownserver.common.ResultCode.GET_DONATE_INFO_SUCCESS;

/**
 * 捐赠服务实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DonateServiceImpl implements DonateService {

    private final FileUtil minioClient;
    @Override
    public ResponseResult getDonateInfo() {
        String imagePath = minioClient.preview("donate/IMG_5831(20230627-155904).JPG");
        String newUrl = imagePath.replace("http://127.0.0.1:9999", "http://omks3oamocpy.xiaomiqiu.com");
        return ResponseResult.success(GET_DONATE_INFO_SUCCESS, newUrl);
    }
}
