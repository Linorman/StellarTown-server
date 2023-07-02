package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.service.PostService;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.ReturnPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_post")
public class PostServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostService {

    private final PostInfoMapper postInfoMapper;

    @Override
    public ResponseResult getAllPosts() {
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<PostInfo> postList = postInfoMapper.selectList(queryWrapper);
        if (postList.size() == 0) {
            log.info("帖子不存在");
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        return ResponseResult.success(ResultCode.POST_LIST_GET_SUCCESS, postList);
    }

    @Override
    public ResponseResult getPost(Integer id) {
        PostInfo postInfo1 = postInfoMapper.selectById(id);
        if (postInfo1 == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        // 根据 postId 查询对应的 PostInfo 对象
        ReturnPost posts = new ReturnPost();
        posts.setId(postInfo1.getId());
        posts.setPostTime(postInfo1.getPostTime());
        posts.setImage(postInfo1.getImage());
        posts.setAddress(postInfo1.getAddress());
        posts.setTitle(postInfo1.getTitle());
        posts.setContent(postInfo1.getContent());
        posts.setLikeCount(postInfo1.getLikeCount());
        posts.setUserId(postInfo1.getUserId());
        posts.setShotTime(postInfo1.getShotTime());
        posts.setTag(postInfo1.getTag());

        return ResponseResult.success(ResultCode.POST_GET_SUCCESS, posts);
    }

    @Override
    public ResponseResult post(PostInfo postInfo) {

        String title = postInfo.getTitle();
        String content = postInfo.getContent();
        if (title == null && content == null ) {
            return ResponseResult.error(ResultCode.POST_ADD_ERROR, null);
        }
        PostInfo post = new PostInfo();

        // post.setImage(postInfo.getImage());
        post.setContent(postInfo.getContent());
        post.setTitle(postInfo.getTitle());
        post.setAddress(postInfo.getAddress());
        post.setTag(post.getTag());
        Integer userId = SecurityUtil.getUserId();
        post.setUserId(userId);
        postInfoMapper.insert(post);
        int id =post.getId();
        return ResponseResult.success(ResultCode.POST_ADD_SUCCESS, id);
    }

    @Override
    public ResponseResult deletePost(PostInfo postInfo) {
        int id = postInfo.getId();
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getId, id);
        PostInfo postInfoTemp = postInfoMapper.selectById(id);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }

        int result = postInfoMapper.delete(queryWrapper);
        if (result > 0) {
            return ResponseResult.success(ResultCode.POST_DELETE_SUCCESS, null);
        } else {
            return ResponseResult.error(ResultCode.POST_DELETE_ERROR, null);
        }
    }

    @Override
    public ResponseResult getUserPost() {
        int id = SecurityUtil.getUserId();
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getUserId, id);
        List<PostInfo> postInfoTemp = postInfoMapper.selectList(queryWrapper);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_LIST_NULL, null);
        }
        List<ReturnPost> postInfoList = new ArrayList<>();
        for (PostInfo postInfo : postInfoTemp) {
            // 根据 postId 查询对应的 PostInfo 对象
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
    public ResponseResult getOthersPost(Integer id) {
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getUserId, id);
        List<PostInfo> postInfoTemp = postInfoMapper.selectList(queryWrapper);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_LIST_NULL, null);
        }
        List<ReturnPost> postInfoList = new ArrayList<>();
        for (PostInfo postInfo : postInfoTemp) {
            // 根据 postId 查询对应的 PostInfo 对象
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
        return ResponseResult.success(ResultCode.POST_LIST_GET_SUCCESS, postInfoList);
    }

}


