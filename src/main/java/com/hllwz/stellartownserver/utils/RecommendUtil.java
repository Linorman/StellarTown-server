package com.hllwz.stellartownserver.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RecommendUtil {
    private static JdbcTemplate jdbcTemplate;

    // 计算用户相似度矩阵
    public static Map<Integer, Map<Integer, Double>> calculateUserSimilarityMatrix(List<Integer> userIds) {
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
    public static double calculateCosineSimilarity(int userIdA, int userIdB) {
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

    public static List<Integer> getLikedPostsByUserId(int userId) {
        String query = "SELECT post_id FROM post_follower_info WHERE liker_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, userId);
        List<Integer> likedPosts = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            int postId = (int) row.get("post_id");
            likedPosts.add(postId);
        }
        return likedPosts;
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
    public static List<Integer> recommendPostsToUser(int targetUserId, int k, Map<Integer, Map<Integer, Double>> similarityMatrix) {
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

    @Autowired
    public void RecommendationUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

