package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@DS("db_stellar_town_post")
public class LikeServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements LikeService {
    @Autowired
    private PostFollowerInfoMapper postFollowerInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PostInfoMapper postInfoMapper;

    @Override
    public ResponseResult like(UserInfo userInfo, PostInfo postInfo) {
        int userId = userInfo.getId();
        int postId = postInfo.getId();
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getId, userId);
        UserInfo userInfoTemp = userInfoMapper.selectById(userId);
        LambdaQueryWrapper<PostInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PostInfo::getId, postId);
        PostInfo postInfoTemp = postInfoMapper.selectById(postId);
        if (userInfoTemp == null) {
            return ResponseResult.error(ResultCode.USER_NOT_EXIST, null);
        }
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }

        PostFollowerInfo postLike = new PostFollowerInfo();
        postLike.setFollowerId(userInfo.getId());
        postLike.setPostId(postInfo.getId());
        postFollowerInfoMapper.insert(postLike);
        return ResponseResult.success(ResultCode.POST_LIKE_SUCCESS, null);
    }

    @Override
    public ResponseResult unLike(UserInfo userInfo, PostInfo postInfo) {
        int userId = userInfo.getId();
        int postId = postInfo.getId();
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getId, userId);
        UserInfo userInfoTemp = userInfoMapper.selectById(userId);

        LambdaQueryWrapper<PostInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PostInfo::getId, postId);
        PostInfo postInfoTemp = postInfoMapper.selectById(postId);

        if (userInfoTemp == null) {
            return ResponseResult.error(ResultCode.USER_NOT_EXIST, null);
        }
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(PostFollowerInfo::getFollowerId, userId);
        queryWrapper2.eq(PostFollowerInfo::getPostId, postId);
        PostFollowerInfo postFollowerInfoTemp = postFollowerInfoMapper.selectOne(queryWrapper2);
        if (postFollowerInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_CANCEL_LIKE_ERROR, null);
        }
        PostFollowerInfo postunLike = new PostFollowerInfo();
        postunLike.setDelFlag(1);
        postFollowerInfoMapper.updateById(postFollowerInfoTemp);
        return ResponseResult.success(ResultCode.POST_CANCEL_LIKE_SUCCESS, null);

    }
}
