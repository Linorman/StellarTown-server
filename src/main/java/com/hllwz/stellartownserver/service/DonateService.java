package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.common.ResponseResult;

/**
 * 捐赠服务接口类
 * @author Linorman
 * @version 1.0.0
 */
public interface DonateService {
    /**
     * 获取捐赠信息
     * @return 捐赠信息
     */
    ResponseResult getDonateInfo();
}
