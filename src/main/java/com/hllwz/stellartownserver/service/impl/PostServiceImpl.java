package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@DS("db_stellar_town_post")
public class PostServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostService {
    @Autowired
    private PostInfoMapper postInfoMapper;
    @Override
    public ResponseResult getAllPosts(){
        String tableName = "post_info";
        if(postInfoMapper.selectPostInfo(tableName) == null){
            log.info("帖子不存在");
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        List<PostInfo> temp = postInfoMapper.selectPostInfo(tableName);
        return ResponseResult.success(ResultCode.POST_LIST_GET_SUCCESS, temp);
    }
    @Override
    public ResponseResult getPost(PostInfo postInfo){
        int id = postInfo.getId();
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getId, id);
        PostInfo goodsInfoTemp = postInfoMapper.selectById(id);
        if (goodsInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        return ResponseResult.success(ResultCode.SUCCESS, goodsInfoTemp);
    }
    @Override
    public ResponseResult post(PostInfo postInfo){

        String title = postInfo.getTitle();
        String content = postInfo.getContent();
        String image = postInfo.getImage();
        if(title==null&content==null&image==null){
            return ResponseResult.error(ResultCode.POST_ADD_ERROR,null);
        }
        PostInfo post=new PostInfo();
        post.setImage(postInfo.getTitle());
        post.setContent(postInfo.getContent());
        post.setTitle(postInfo.getTitle());
        post.setUserId(postInfo.getUserId());
        postInfoMapper.insert(post);
//未完成
        return ResponseResult.success(ResultCode.SUCCESS,null);

    }
    @Override
    public ResponseResult deletePost(PostInfo postInfo){
        int id = postInfo.getId();
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getId, id);
        PostInfo postInfoTemp = postInfoMapper.selectById(id);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        postInfoTemp.setDelFlag(1);
        postInfoMapper.updateById(postInfoTemp);
        return ResponseResult.success(ResultCode.SUCCESS,null);
    }

}

