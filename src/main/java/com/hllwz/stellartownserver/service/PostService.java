package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;

/**
 * 帖子信息Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface PostService extends IService<PostInfo> {
    /**
     * 用户获取帖子
     * @param id
     * @return ResponseResult
     */
    ResponseResult getPost(Integer id);

    /**
     * 获取全部帖子
     *
     * @return ResponseResult
     */
    ResponseResult getAllPosts();

    /**
     * 发帖
     * @param  postInfo
     * @return ResponseResult
     */
    ResponseResult post(PostInfo postInfo);

    /**
     * 删帖
     * @param postInfo
     * @return ResponseResult
     */
    ResponseResult deletePost(PostInfo postInfo);

    /**
     * 返回用户发布的帖子
     * @param  id
     * @return ResponseResult
     */
    ResponseResult getOthersPost(Integer id);

    /**
     * 返回其他用户发布的帖子
     *
     * @return ResponseResult
     */
    ResponseResult getUserPost();


}
