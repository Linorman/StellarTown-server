package com.hllwz.stellartownserver.utils;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RecommendUtil {
    private final PostFollowerInfoMapper postFollowerInfoMapper;

    // 计算用户相似度矩阵
    public Map<Integer, Map<Integer, Double>> calculateUserSimilarityMatrix(List<Integer> userIds) {
        Map<Integer, Map<Integer, Double>> similarityMatrix = new HashMap<>();
        int totalUsers = userIds.size();
        for (int i = 0; i < totalUsers; i++) {
            int userIdA = userIds.get(i);
            Map<Integer, Double> similarityRow = new HashMap<>();

            for (int j = 0; j < totalUsers; j++) {
                int userIdB = userIds.get(j);

                if (userIdA != userIdB) {
                    double similarity = calculateCosineSimilarity(userIdA, userIdB);
                    similarityRow.put(userIdB, similarity);
                }
            }
            similarityMatrix.put(userIdA, similarityRow);
        }
        return similarityMatrix;
    }

    // 计算余弦相似度
    public double calculateCosineSimilarity(int userIdA, int userIdB) {
        List<Integer> postIdsA = getLikedPostsByUserId(userIdA);
        List<Integer> postIdsB = getLikedPostsByUserId(userIdB);
        // 计算用户A和用户B共同喜欢的帖子数量
        int commonLikedPosts = countCommonLikedPosts(postIdsA, postIdsB);

        // 计算用户A和用户B分别喜欢帖子的个数
        int likedPostsA = postIdsA.size();
        int likedPostsB = postIdsB.size();

        // 计算余弦相似度
        double similarity = commonLikedPosts / Math.sqrt(likedPostsA * likedPostsB);
        return similarity;
    }

    @DS("db_stellar_town_post")
    public List<Integer> getLikedPostsByUserId(int userId) {
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getLikerId, userId);
        List<PostFollowerInfo> postList = postFollowerInfoMapper.selectList(queryWrapper);
        List<Integer>postInfoList = new ArrayList<>();
        for (PostFollowerInfo postfollowerInfo : postList) {
            int postId = postfollowerInfo.getPostId();
            postInfoList.add(postId);
        }
        return postInfoList;
        }


    // 计算共同喜欢的帖子数量
    public static int countCommonLikedPosts(List<Integer> postIdsA, List<Integer> postIdsB) {
        int count = 0;
        for (int postId : postIdsA) {
            if (postIdsB.contains(postId)) {
                count++;
            }
        }
        return count;
    }

    // 根据用户相似度矩阵推荐帖子给目标用户
    public List<Integer> recommendPostsToUser(int targetUserId, int k, Map<Integer, Map<Integer, Double>> similarityMatrix) {
        List<Integer> recommendedPosts = new ArrayList<>();
        Map<Integer, Double> similarityRow = similarityMatrix.get(targetUserId);

        // 选择与目标用户兴趣最相似的K个用户
        List<Map.Entry<Integer, Double>> sortedSimilarities = new ArrayList<>(similarityRow.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        for (int i = 0; i < Math.min(k, sortedSimilarities.size()); i++) {
            int similarUserId = sortedSimilarities.get(i).getKey();
            // 获取这 K 个用户喜欢的帖子列表
            List<Integer> likedPosts = getLikedPostsByUserId(similarUserId);
            recommendedPosts.addAll(likedPosts);
        }
        Collections.sort(recommendedPosts);
        return recommendedPosts;
    }
}
