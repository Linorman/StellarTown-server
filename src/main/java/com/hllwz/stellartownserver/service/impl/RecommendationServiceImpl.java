package com.hllwz.stellartownserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.entity.weatherApi.Location;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.RecommendationService;
import com.hllwz.stellartownserver.utils.CityUtil;
import com.hllwz.stellartownserver.utils.RecommendUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.ReturnPosts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements RecommendationService {
    private final RecommendUtil recommendUtil;
    private final UserInfoMapper userInfoMapper;
    private final PostServiceImpl postService;

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
        List<ReturnPosts> postList11 = new ArrayList<>();
        for (Integer postId : recommendedPosts) {
            ResponseResult<ReturnPosts> postList1 = postService.getPost(postId);
            ReturnPosts posts1 = postList1.getData();
            if (postId != null) {
                postList11.add(posts1);
            }
        }
//        依据地点远近进行排序
            for (ReturnPosts newPost : postList11) {
                Map<String,String>location1 = CityUtil.getLocationByCity(SecurityUtil.getLoginUser().getAddress());
                if(location1==null){
                    return  ResponseResult.error(ResultCode.WEATHER_API_ERROR,null);
                }

                double userLat = Double.parseDouble(location1.get("lat"));
                double userLon = Double.parseDouble(location1.get("lon"));

                Map<String,String>location2 = CityUtil.getLocationByCity(newPost.getAddress());
                if(location2==null){
                    return  ResponseResult.error(ResultCode.WEATHER_API_ERROR,null);
                }
                double postLat = Double.parseDouble(location2.get("lat"));
                double postLon = Double.parseDouble(location2.get("lon"));
                double distance = recommendUtil.calculateDistance(userLat, userLon, postLat, postLon);
                newPost.setDistance(distance);
            }
            Collections.sort(postList11, Comparator.comparing(ReturnPosts::getDistance));

        return ResponseResult.success(ResultCode.SUCCESS, postList11);
    }
}

