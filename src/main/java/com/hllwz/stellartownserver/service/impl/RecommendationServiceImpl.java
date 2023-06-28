package com.hllwz.stellartownserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.RecommendationService;
import com.hllwz.stellartownserver.utils.RecommendUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements RecommendationService {
    private final RecommendUtil recommendUtil;
    private final UserInfoMapper userInfoMapper;
    private  final PostFollowerServiceImpl postFollowerService;

    public ResponseResult getRecommendation() {
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
        return ResponseResult.success(ResultCode.SUCCESS, recommendedPosts);
    }
}

