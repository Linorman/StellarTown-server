package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;

/**
 * 帖子信息Service接口
 * @author Lyh
 * @version 1.0.0
 */
public interface PostService extends IService<PostInfo> {
    /**
     * 用户获取帖子
     * @return ResponseResult
     */
    ResponseResult getPost(PostInfo postInfo);

    /**
     * 获取全部帖子
     * @return ResponseResult
     */
    ResponseResult getAllPosts();

    /**
     * 发帖
     * @return ResponseResult
     */
    ResponseResult post(PostInfo postInfo);

    /**
     * 删帖
     * @return ResponseResult
     */
    ResponseResult deletePost(PostInfo postInfo);

}
