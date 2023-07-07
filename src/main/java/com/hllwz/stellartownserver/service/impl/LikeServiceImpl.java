package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.service.LikeService;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 点赞Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_post")
public class LikeServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements LikeService {

    private final PostFollowerInfoMapper postFollowerInfoMapper;
    private final PostFollowerServiceImpl postFollowerService;
    private final PostInfoMapper postInfoMapper;

    @Override
    public ResponseResult like(PostInfo postInfo) {

        int postId = postInfo.getId();
        int userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<PostInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PostInfo::getId, postId);
        PostInfo postInfoTemp = postInfoMapper.selectById(postId);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getPostId, postId);
        queryWrapper.eq(PostFollowerInfo::getLikerId,userId);
        PostFollowerInfo temp = postFollowerInfoMapper.selectOne(queryWrapper);
        if (temp!=null){
            return ResponseResult.error(ResultCode.POST_LIKEPOST_EXIST,null);
        }

        PostFollowerInfo postLike = new PostFollowerInfo();
        postLike.setLikerId(userId);
        postLike.setPostId(postInfo.getId());
        postFollowerInfoMapper.insert(postLike);
        PostInfo postInfo1 = postInfoMapper.selectById(postId);
        postInfo1.setLikeCount(postInfo1.getLikeCount()+1);
        postInfoMapper.updateById(postInfo1);
        return ResponseResult.success(ResultCode.POST_LIKE_SUCCESS, null);
    }

    @Override
    public ResponseResult unLike(PostInfo postInfo) {
        int userId = SecurityUtil.getUserId();
        int postId = postInfo.getId();
        LambdaQueryWrapper<PostInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PostInfo::getId, postId);
        PostInfo postInfoTemp = postInfoMapper.selectById(postId);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(PostFollowerInfo::getPostId, postId);
        queryWrapper2.eq(PostFollowerInfo::getLikerId,userId);
        PostFollowerInfo postFollowerInfoTemp = postFollowerInfoMapper.selectOne(queryWrapper2);
        if (postFollowerInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_CANCEL_LIKE_ERROR, null);
        }
        int result = postFollowerInfoMapper.delete(queryWrapper2);
        if (result > 0) {
            return ResponseResult.success(ResultCode.POST_CANCEL_LIKE_SUCCESS, null);
        } else {
            return ResponseResult.error(ResultCode.POST_CANCEL_LIKE_ERROR, null);
        }
    }

}

