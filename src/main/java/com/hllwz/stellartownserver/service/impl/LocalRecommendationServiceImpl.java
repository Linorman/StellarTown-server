package com.hllwz.stellartownserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.LocalRecommendationService;
import com.hllwz.stellartownserver.utils.RecommendUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.ReturnPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 同城帖子推荐Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LocalRecommendationServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements LocalRecommendationService {
    private final RecommendUtil recommendUtil;
    private final UserInfoMapper userInfoMapper;

    private final PostServiceImpl postService;

    public ResponseResult getLocalRecommendation() {
       UserInfo userInfo = userInfoMapper.selectById(SecurityUtil.getUserId());
        String address= userInfo.getAddress();
        List<Integer> userIds = new ArrayList<>();
        // 遍历PostFollowerInfo表，获取所有用户的ID，并加入userIds列表
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        List<UserInfo> userList = userInfoMapper.selectList(wrapper);
        for (UserInfo user : userList) {
            userIds.add(user.getId());
        }
        if (userIds.size() == 0) {
            return ResponseResult.error(ResultCode.USER_LIST_EMPTY, null);
        }
        Map<Integer, Map<Integer, Double>> similarityMatrix = recommendUtil.calculateUserSimilarityMatrix(userIds);
        int targetUserId = SecurityUtil.getUserId(); // 目标用户ID
        int k = userIds.size(); // 推荐数量
        List<Integer> recommendedPosts = recommendUtil.recommendPostsToUser(targetUserId, k, similarityMatrix);
        List<ReturnPost> postList11= new ArrayList<>();
        for(Integer postId : recommendedPosts){
            ResponseResult<ReturnPost> postList1 = postService.getPost(postId);
            ReturnPost posts1 = postList1.getData();
            if(postId!=null && posts1.getAddress().equals(address)){
                postList11.add(posts1);
            }
        }
        return ResponseResult.success(ResultCode.SUCCESS, postList11);
    }
}

