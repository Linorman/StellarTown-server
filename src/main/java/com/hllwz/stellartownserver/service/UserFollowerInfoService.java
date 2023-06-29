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
     * @param userInfo
     * @return ResponseResult
     */
    ResponseResult followUser(UserInfo userInfo);

    /**
     * 取消关注用户
     *
     * @param userInfo
     * @return ResponseResult
     */
    ResponseResult unFollowUser(UserInfo userInfo);

    /**
     * 返回关注用户
     *
     * @return ResponseResult
     */
    ResponseResult getUserFollow();

    /**
     * 返回他人关注
     *
     * @param id
     * @return ResponseResult
     */
    ResponseResult getOthersFollow(Integer id);

    /**
     * 返回粉丝
     *
     * @return ResponseResult
     */
    ResponseResult getFans();

    /**
     * 返回他人粉丝
     *
     * @param id
     * @return ResponseResult
     */
    ResponseResult getOthersFans(Integer id);

    /**
     * 返回是否点赞
     *
     * @param followId
     * @return ResponseResult
     */
    ResponseResult isFollowed(Integer followId);
}