package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.mapper.RecommendationMapper;
import com.hllwz.stellartownserver.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_post")
public class RecommendationServiceImpl extends ServiceImpl<RecommendationMapper, PostFollowerInfo> implements RecommendationService {

    private final RecommendationMapper recommendationMapper;

    @Override
    public ResponseResult getRecommendation(PostFollowerInfo postFollowerInfo1){
        int likerId = postFollowerInfo1.getLikerId();
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, likerId);
        PostFollowerInfo postFollowerInfoTemp = recommendationMapper.selectById(likerId);
        if (postFollowerInfoTemp == null) {
            return ResponseResult.error(ResultCode.USER_NOT_EXIST, null);//userliker不存在
        }
            // 查询用户喜欢的帖子列表
        List<PostFollowerInfo> likedPosts = recommendationMapper.selectList(queryWrapper);

            // 获取所有用户点赞的帖子集合
        List<PostFollowerInfo> allPostLikes = recommendationMapper.selectList(null);

            // 构建帖子ID与点赞该帖子的用户ID集合的映射关系
        Map<Integer, Set<Integer>> postLikedByUsers = new HashMap<>();
        for (PostFollowerInfo postFollowerInfo : allPostLikes) {
            Integer postId = postFollowerInfo.getPostId();
            Integer FollowerId = postFollowerInfo.getLikerId();
            postLikedByUsers.computeIfAbsent(postId, k -> new HashSet<>()).add(FollowerId);
        }

            // 计算其他用户与当前用户的相似度（可以使用余弦相似度等方法）
        Map<Integer, Double> similarityMap = new HashMap<>();
        for (Integer postId : postLikedByUsers.keySet()) {
            Set<Integer> likedByUsers = postLikedByUsers.get(postId);
                if (likedByUsers.contains(likerId)) {
                    for (Integer otherUserId : likedByUsers) {
                        if (!otherUserId.equals(likerId)) {
                            similarityMap.put(otherUserId, similarityMap.getOrDefault(otherUserId, 0.0) + 1.0);
                        }
                    }
                }
            }

            // 排序相似度并选取相似度较高的用户
        List<Integer> similarUserIds = similarityMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)  // 选择前10个最相似的用户作为推荐依据
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

            // 获取选定的用户喜欢的帖子，并筛除当前用户已喜欢的帖子
        Set<Integer> likedPostIds = likedPosts.stream().map(PostFollowerInfo::getPostId).collect(Collectors.toSet());
        queryWrapper.eq(PostFollowerInfo::getLikerId, similarUserIds);
        List<PostFollowerInfo> similarUserLikedPosts = recommendationMapper.selectList(queryWrapper);
        List<PostFollowerInfo> recommendations = similarUserLikedPosts.stream()
                .filter(post -> !likedPostIds.contains(postFollowerInfo1.getPostId()))
                .collect(Collectors.toList());

        return ResponseResult.success(ResultCode.SUCCESS,recommendations);


    }



}