package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.mapper.PostInfoMapper;
import com.hllwz.stellartownserver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_post")
public class PostServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostService {

    private final PostInfoMapper postInfoMapper;

    @Override
    public ResponseResult getAllPosts(){
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<PostInfo> postList = postInfoMapper.selectList(queryWrapper);
        if(postList.size() == 0){
            log.info("帖子不存在");
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        return ResponseResult.success(ResultCode.POST_LIST_GET_SUCCESS, postList);
    }
    @Override
    public ResponseResult getPost(PostInfo postInfo){
        int id = postInfo.getId();
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getId, id);
        PostInfo postInfoTemp = postInfoMapper.selectById(id);
        if (postInfoTemp == null) {
            return ResponseResult.error(ResultCode.POST_NOT_FOUND, null);
        }
        return ResponseResult.success(ResultCode.SUCCESS, postInfoTemp);
    }
    @Override
    public ResponseResult post(PostInfo postInfo){

        String title = postInfo.getTitle();
        String content = postInfo.getContent();
        String image = postInfo.getImage();
        if(title==null&content==null&image==null){
            return ResponseResult.error(ResultCode.POST_ADD_ERROR,null);
        }
        PostInfo post= new PostInfo();
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


