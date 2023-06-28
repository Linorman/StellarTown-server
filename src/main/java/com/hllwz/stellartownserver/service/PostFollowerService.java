package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;

import java.util.List;

/**
 * 帖子点赞Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface PostFollowerService extends IService<PostFollowerInfo> {
    /**
     * 返回点赞数
     *
     * @return ResponseResult
     */
    ResponseResult getLikes(PostFollowerInfo postFollowerInfo);

    /**
     * 返回点赞帖子
     *
     * @return ResponseResult
     */

    ResponseResult getLikedPosts();

    /**
     * 返回点赞帖子重写
     *
     * @return ResponseResult
     */
    ResponseResult<List<Integer>> getLikedPosts(int id);


    ResponseResult isLiked(Integer postId);
}