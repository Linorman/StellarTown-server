package com.hllwz.stellartownserver.service.impl;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.mapper.PostFollowerInfoMapper;
import com.hllwz.stellartownserver.service.PostFollowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@DS("db_stellar_town_post")
public class PostFollowerServiceImpl extends ServiceImpl<PostFollowerInfoMapper, PostFollowerInfo> implements PostFollowerService {
    @Autowired
    private PostFollowerInfoMapper postFollowerInfoMapper;
    public PostFollowerServiceImpl(PostFollowerInfoMapper postFollowerInfoMapper){
        this.postFollowerInfoMapper = postFollowerInfoMapper;
    }
    @Override
    public ResponseResult getLikes(PostFollowerInfo postFollowerInfo) {

        int postId = postFollowerInfo.getPostId();
        LambdaQueryWrapper<PostFollowerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFollowerInfo::getPostId, postId);
        PostFollowerInfo postFollowerInfoTemp = postFollowerInfoMapper.selectById(postId);
        if (postFollowerInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        int temp = postFollowerInfoMapper.selectCount(queryWrapper);

        return ResponseResult.success(ResultCode.SUCCESS, temp);

    }
}