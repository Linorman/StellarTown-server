package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;

/**
 * 帖子推荐Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface LocalRecommendationService extends IService<PostFollowerInfo> {
    /**
     * 推荐帖子
     *
     * @return ResponseResult
     */
    ResponseResult getLocalRecommendation();
}
