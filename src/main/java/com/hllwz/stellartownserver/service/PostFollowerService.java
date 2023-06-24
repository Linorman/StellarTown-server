package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.PostInfo;

public interface PostFollowerService extends IService<PostFollowerInfo> {
    ResponseResult getLikes(PostFollowerInfo postFollowerInfo);



}