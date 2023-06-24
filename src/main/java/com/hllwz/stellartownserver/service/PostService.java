package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;

public interface PostService extends IService<PostInfo> {
     ResponseResult getPost(PostInfo postInfo);
     ResponseResult getAllPosts();
     ResponseResult post(PostInfo postInfo);
     ResponseResult deletePost(PostInfo postInfo);

}
