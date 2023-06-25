package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.PostInfo;

public interface RecommendationService extends IService<PostFollowerInfo> {
    ResponseResult getRecommendation(PostFollowerInfo postFollowerInfo);
}
