package com.hllwz.stellartownserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.MinioService;
import com.hllwz.stellartownserver.service.PostService;
import com.hllwz.stellartownserver.utils.FileUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import io.minio.*;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio服务实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final FileUtil minioClient;
    private final PostInfoMapper postInfoMapper;
    private final UserInfoMapper userInfoMapper;
    @Override
    public ResponseResult uploadAvatar(String objectName, InputStream inputStream) {
        String fileName = "avatar-"+ "userId-" + SecurityUtil.getUserId() + "-" + objectName;
        boolean flag = minioClient.upload(fileName, inputStream);
        if (flag) {
            Integer userId = SecurityUtil.getUserId();
            LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfo::getId, userId);
            UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

            String newUrl = "http://omks3oamocpy.xiaomiqiu.com/" + fileName;
            userInfo.setAvatar(newUrl);
            userInfoMapper.updateById(userInfo);

            return ResponseResult.success(ResultCode.AVATAR_UPLOAD_SUCCESS, newUrl);
        } else {
            return ResponseResult.error(ResultCode.AVATAR_UPLOAD_ERROR, null);
        }

    }

    @Override
    public ResponseResult uploadPost(String objectName, InputStream inputStream, Integer postId) {
        String fileName = "post-" + "postId-" + postId + "-" + objectName;
        boolean flag = minioClient.upload("post" + objectName, inputStream);
        if (flag) {
            LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PostInfo::getId, postId);
            PostInfo postInfo = postInfoMapper.selectOne(queryWrapper);
            // String postUrl = minioClient.preview("post" + objectName);
            String postUrl = "http://omks3oamocpy.xiaomiqiu.com/" + fileName;
            postInfo.setImage(postUrl);
            return ResponseResult.success(ResultCode.POST_UPLOAD_SUCCESS, postUrl);
        } else {
            return ResponseResult.error(ResultCode.POST_UPLOAD_ERROR, null);
        }
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            // return minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(objectName).build()));
        } catch (MinioException e) {
            // 处理异常
            return null;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
