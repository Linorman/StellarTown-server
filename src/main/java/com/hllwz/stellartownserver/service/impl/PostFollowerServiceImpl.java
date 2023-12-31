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
import com.hllwz.stellartownserver.service.PostFollowerService;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.ReturnPost;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * 点赞帖子Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_post")
public class PostFollowerServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements PostFollowerService {

    private final PostFollowerInfoMapper postFollowerInfoMapper;
    private final PostInfoMapper postInfoMapper;

    @Override
    public ResponseResult getLikes(PostFollowerInfo postFollowerInfo) {
        int postId = postFollowerInfo.getPostId();
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getPostId, postId);
        PostFollowerInfo postFollowerInfoTemp = postFollowerInfoMapper.selectById(postId);
        if (postFollowerInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_FOLLOWER_NUM_GET_ERROR, null);//修改”帖子不存在或未被点赞“
        }
        long temp = postFollowerInfoMapper.selectCount(queryWrapper);

        return ResponseResult.success(ResultCode.POST_FOLLOWER_NUM_GET_SUCCESS, temp);

    }

    @Override
    public ResponseResult getLikedPosts() {
        int likerId = SecurityUtil.getUserId();
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, likerId);
        List<PostFollowerInfo> temp = postFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.POST_GET_ERROR, null);//修改”用户不存在或未点过赞“
        }
        List<PostFollowerInfo> postList = postFollowerInfoMapper.selectList(queryWrapper);

        List<ReturnPost> postInfoList = new ArrayList<>();
        for (PostFollowerInfo postfollowerInfo : postList) {
            // 根据 postId 查询对应的 PostInfo 对象
            PostInfo postInfo = postInfoMapper.selectById(postfollowerInfo.getPostId());
            ReturnPost posts = new ReturnPost();
            posts.setId(postInfo.getId());
            posts.setPostTime(postInfo.getPostTime());
            posts.setImage(postInfo.getImage());
            posts.setAddress(postInfo.getAddress());
            posts.setTitle(postInfo.getTitle());
            posts.setContent(postInfo.getContent());
            posts.setLikeCount(postInfo.getLikeCount());
            posts.setUserId(postInfo.getUserId());
            posts.setShotTime(postInfo.getShotTime());
            if (postInfo != null) {
                postInfoList.add(posts);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, postInfoList);
    }

    @Override
    public ResponseResult<List<Integer>> getLikedPosts(int id) {
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, id);
        List<PostFollowerInfo> temp = postFollowerInfoMapper.selectList(queryWrapper);
        List<Integer> postIdList = new ArrayList<>();
        for (PostFollowerInfo postfollowerInfo : temp) {
            PostInfo postInfo = postInfoMapper.selectById(postfollowerInfo.getPostId());
            if (postInfo != null) {
                postIdList.add(postInfo.getId());
            }
        }
        return ResponseResult.success(ResultCode.POST_LIST_GET_SUCCESS, postIdList);

    }
    @Override
    public  ResponseResult getOthersLikedPosts(Integer id){
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, id);
        List<PostFollowerInfo> temp = postFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.POST_GET_ERROR, null);//修改”用户不存在或未点过赞“
        }
        List<PostFollowerInfo> postList = postFollowerInfoMapper.selectList(queryWrapper);

        List<ReturnPost> postInfoList = new ArrayList<>();
        for (PostFollowerInfo postfollowerInfo : postList) {
            // 根据 postId 查询对应的 PostInfo 对象
            PostInfo postInfo = postInfoMapper.selectById(postfollowerInfo.getPostId());
            ReturnPost posts = new ReturnPost();
            posts.setId(postInfo.getId());
            posts.setPostTime(postInfo.getPostTime());
            posts.setImage(postInfo.getImage());
            posts.setAddress(postInfo.getAddress());
            posts.setTitle(postInfo.getTitle());
            posts.setContent(postInfo.getContent());
            posts.setLikeCount(postInfo.getLikeCount());
            posts.setUserId(postInfo.getUserId());
            posts.setShotTime(postInfo.getShotTime());
            if (postInfo != null) {
                postInfoList.add(posts);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, postInfoList);
    }



    @Override
    public ResponseResult<Boolean> isLiked(Integer postId) {
        int userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, userId);
        queryWrapper.eq(PostFollowerInfo::getPostId, postId);
        PostFollowerInfo temp = postFollowerInfoMapper.selectOne(queryWrapper);
        if (temp == null) {
            return ResponseResult.success(ResultCode.POST_LIKEPOST_NO, false);
        } else {
            return ResponseResult.success(ResultCode.POST_LIKEPOST_EXIST, true);
        }
    }
}