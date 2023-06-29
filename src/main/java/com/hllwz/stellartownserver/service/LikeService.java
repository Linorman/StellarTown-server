package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.PostInfo;

/**
 * 点赞Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface LikeService extends IService<PostFollowerInfo> {

    /**
     * 点赞
     *
     * @param postInfo
     * @return ResponseResult
     */
    ResponseResult like(PostInfo postInfo);

    /**
     * 取消点赞
     *
     * @param postInfo
     * @return ResponseResult
     */
    ResponseResult unLike(PostInfo postInfo);
}
