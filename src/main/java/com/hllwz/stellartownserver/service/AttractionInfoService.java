package com.hllwz.stellartownserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.AttractionInfo;
import com.hllwz.stellartownserver.entity.UserInfo;

/**
 * 推荐景点Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface AttractionInfoService extends IService<AttractionInfo> {

    /**
     * 推荐景点
     *
     * @return ResponseResult
     */
    ResponseResult getAttraction(UserInfo userInfo);

}

