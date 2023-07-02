package com.hllwz.stellartownserver.utils;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.impl.PostFollowerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RecommendUtil {
    private  final PostFollowerServiceImpl postFollowerService;

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

    public List<Integer> getLikedPostsByUserId(int userId) {
        ResponseResult<List<Integer>> postList1 = postFollowerService.getLikedPosts(userId);
        List<Integer>postList2=postList1.getData();
        return postList2;
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
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // 地球半径，单位千米

        // 将角度转换为弧度
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // 应用 Haversine 公式计算距离
        double dLon = lon2Rad - lon1Rad;
        double dLat = lat2Rad - lat1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }

    // 根据用户相似度矩阵推荐帖子给目标用户
    public List<Integer> recommendPostsToUser(int targetUserId, int k, Map<Integer, Map<Integer, Double>> similarityMatrix) {
        List<Integer> recommendedPosts = new ArrayList<>();
        Map<Integer, Double> similarityRow = similarityMatrix.get(targetUserId);

        // 选择与目标用户兴趣最相似的K个用户
        List<Map.Entry<Integer, Double>> sortedSimilarities = new ArrayList<>(similarityRow.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        Set<Integer> recommendedPostSet = new HashSet<>();
        for (int i = 0; i < Math.min(k, sortedSimilarities.size()); i++) {
            int similarUserId = sortedSimilarities.get(i).getKey();
            // 获取这 K 个用户喜欢的帖子列表
            List<Integer> likedPosts = getLikedPostsByUserId(similarUserId);
            recommendedPostSet.addAll(likedPosts);
        }
        List<Integer> recommendedPosts1 = new ArrayList<>(recommendedPostSet);
        Collections.sort(recommendedPosts1);
        return recommendedPosts1;
    }
}
