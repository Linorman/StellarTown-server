package com.hllwz.stellartownserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.UserFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;

/**
 * 关注用户Service接口
 *
 * @author Lyh
 * @version 1.0.0
 */
public interface UserFollowerInfoService extends IService<UserFollowerInfo> {
    /**
     * 关注用户
     *
     * @return ResponseResult
     */
    ResponseResult followUser(UserInfo userInfo);
    /**
     * 取消关注用户
     *
     * @return ResponseResult
     */
    ResponseResult unFollowUser(UserInfo userInfo);
    /**
     * 返回关注用户
     *
     * @return ResponseResult
     */
    ResponseResult getUserFollow();
}