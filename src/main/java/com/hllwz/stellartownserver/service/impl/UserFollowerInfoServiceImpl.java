package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.UserFollowerInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.UserFollowerInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.UserFollowerInfoService;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.OtherUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_follower")
public class UserFollowerInfoServiceImpl extends ServiceImpl<UserFollowerInfoMapper, UserFollowerInfo> implements UserFollowerInfoService {

    private final UserFollowerInfoMapper userFollowerInfoMapper;
    private final UserInfoMapper userInfoMapper;

    @Override
    public ResponseResult followUser(UserInfo userInfo) {
        int followerId = userInfo.getId();//关注的用户的id
        int userId = SecurityUtil.getUserId();
        if (followerId == userId) {
            return ResponseResult.error(ResultCode.FOLLOW_USER_NOT_SELF, null);
        }
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getFollowerId, followerId);
        queryWrapper.eq(UserFollowerInfo::getUserId, userId);
        UserFollowerInfo count = userFollowerInfoMapper.selectOne(queryWrapper);
        if (count != null) {
            return ResponseResult.error(ResultCode.FOLLOW_USER_EXIST, null);
        }
        UserFollowerInfo userFollower = new UserFollowerInfo();
        userFollower.setFollowerId(followerId);
        userFollower.setUserId(userId);
        userFollowerInfoMapper.insert(userFollower);
        return ResponseResult.success(ResultCode.FOLLOW_USER_SUCCESS, null);
    }

    @Override
    public ResponseResult unFollowUser(UserInfo userInfo) {
        int followerId = userInfo.getId();
        int userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getFollowerId, followerId);
        queryWrapper.eq(UserFollowerInfo::getUserId, userId);
        UserFollowerInfo count = userFollowerInfoMapper.selectOne(queryWrapper);
        if (count == null) {
            return ResponseResult.error(ResultCode.UNFOLLOW_USER_NOT_EXIST, null);
        }
        int result = userFollowerInfoMapper.delete(queryWrapper);
        if (result > 0) {
            return ResponseResult.success(ResultCode.UNFOLLOW_USER_SUCCESS, null);
        } else {
            return ResponseResult.error(ResultCode.UNFOLLOW_USER_ERROR, null);
        }

    }

    @Override
    public ResponseResult getUserFollow() {
        int userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getUserId, userId);
        List<UserFollowerInfo> temp = userFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.FOLLOWER_LIST_GET_ERROR, null);
        }
        List<UserFollowerInfo> followerInfoList = userFollowerInfoMapper.selectList(queryWrapper);

        List<OtherUserResponse> userInfoList = new ArrayList<>();
        for (UserFollowerInfo userfollowerInfo : followerInfoList) {
            // 根据 followerId 查询对应的 UserInfo 对象
            UserInfo userInfo = userInfoMapper.selectById(userfollowerInfo.getFollowerId());
            OtherUserResponse userInfoResponse = new OtherUserResponse();
            userInfoResponse.setId(userInfo.getId());
            userInfoResponse.setUsername(userInfo.getUsername());
            userInfoResponse.setAvatar(userInfo.getAvatar());
            userInfoResponse.setPhoneNumber(userInfo.getPhoneNumber());
            userInfoResponse.setAge(userInfo.getAge());
            userInfoResponse.setGender(userInfo.getGender());
            userInfoResponse.setAddress(userInfo.getAddress());
            userInfoResponse.setSignature(userInfo.getSignature());
            if (userInfo != null) {
                userInfoList.add(userInfoResponse);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, userInfoList);
    }

    @Override
    public ResponseResult getOthersFollow(Integer id) {
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getUserId, id);
        List<UserFollowerInfo> temp = userFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.FOLLOWER_LIST_GET_ERROR, null);
        }
        List<UserFollowerInfo> followerInfoList = userFollowerInfoMapper.selectList(queryWrapper);

        List<OtherUserResponse> userInfoList = new ArrayList<>();
        for (UserFollowerInfo userfollowerInfo : followerInfoList) {
            // 根据 followerId 查询对应的 UserInfo 对象
            UserInfo userInfo = userInfoMapper.selectById(userfollowerInfo.getFollowerId());
            OtherUserResponse userInfoResponse = new OtherUserResponse();
            userInfoResponse.setId(userInfo.getId());
            userInfoResponse.setUsername(userInfo.getUsername());
            userInfoResponse.setAvatar(userInfo.getAvatar());
            userInfoResponse.setPhoneNumber(userInfo.getPhoneNumber());
            userInfoResponse.setAge(userInfo.getAge());
            userInfoResponse.setGender(userInfo.getGender());
            userInfoResponse.setAddress(userInfo.getAddress());
            userInfoResponse.setSignature(userInfo.getSignature());
            if (userInfo != null) {
                userInfoList.add(userInfoResponse);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, userInfoList);
    }

    @Override
    public ResponseResult getFans() {
        int id = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getFollowerId, id);
        List<UserFollowerInfo> temp = userFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.FOLLOWER_LIST_GET_ERROR, null);
        }
        List<UserFollowerInfo> fansList = userFollowerInfoMapper.selectList(queryWrapper);

        List<OtherUserResponse> userInfoList = new ArrayList<>();
        for (UserFollowerInfo userfollowerInfo : fansList) {
            // 根据 followerId 查询对应的 UserInfo 对象
            UserInfo userInfo = userInfoMapper.selectById(userfollowerInfo.getUserId());
            OtherUserResponse userInfoResponse = new OtherUserResponse();
            userInfoResponse.setId(userInfo.getId());
            userInfoResponse.setUsername(userInfo.getUsername());
            userInfoResponse.setAvatar(userInfo.getAvatar());
            userInfoResponse.setPhoneNumber(userInfo.getPhoneNumber());
            userInfoResponse.setAge(userInfo.getAge());
            userInfoResponse.setGender(userInfo.getGender());
            userInfoResponse.setAddress(userInfo.getAddress());
            userInfoResponse.setSignature(userInfo.getSignature());
            if (userInfo != null) {
                userInfoList.add(userInfoResponse);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, userInfoList);
    }

    @Override
    public ResponseResult getOthersFans(Integer id) {
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getFollowerId, id);
        List<UserFollowerInfo> temp = userFollowerInfoMapper.selectList(queryWrapper);
        if (temp == null) {
            return ResponseResult.error(ResultCode.FOLLOWER_LIST_GET_ERROR, null);
        }
        List<UserFollowerInfo> fansList = userFollowerInfoMapper.selectList(queryWrapper);

        List<OtherUserResponse> userInfoList = new ArrayList<>();
        for (UserFollowerInfo userfollowerInfo : fansList) {
            // 根据 followerId 查询对应的 UserInfo 对象
            UserInfo userInfo1 = userInfoMapper.selectById(userfollowerInfo.getUserId());
            OtherUserResponse userInfoResponse = new OtherUserResponse();
            userInfoResponse.setId(userInfo1.getId());
            userInfoResponse.setUsername(userInfo1.getUsername());
            userInfoResponse.setAvatar(userInfo1.getAvatar());
            userInfoResponse.setPhoneNumber(userInfo1.getPhoneNumber());
            userInfoResponse.setAge(userInfo1.getAge());
            userInfoResponse.setGender(userInfo1.getGender());
            userInfoResponse.setAddress(userInfo1.getAddress());
            userInfoResponse.setSignature(userInfo1.getSignature());
            if (userInfo1 != null) {
                userInfoList.add(userInfoResponse);
            }
        }
        return ResponseResult.success(ResultCode.FOLLOWER_LIST_GET_SUCCESS, userInfoList);
    }

    @Override
    public ResponseResult isFollowed(Integer followId) {
        int userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFollowerInfo::getUserId, userId);
        queryWrapper.eq(UserFollowerInfo::getFollowerId, followId);
        UserFollowerInfo temp = userFollowerInfoMapper.selectOne(queryWrapper);
        if (temp == null) {
            return ResponseResult.success(ResultCode.UNFOLLOW_USER_NOT_EXIST, false);
        } else {
            return ResponseResult.success(ResultCode.FOLLOW_USER_EXIST, true);
        }
    }

}



