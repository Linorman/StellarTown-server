package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞控制器
 *
 * @author Lyh
 * @version 1.0.0
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/forum")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/like")
    /**
     * 点赞
     * @return ResponseResult
     */
    public ResponseResult like(@RequestBody  PostInfo postInfo) {
        return likeService.like(postInfo);
    }

    @PostMapping("/unLike")
    /**
     * 取消点赞
     * @return ResponseResult
     */
    public ResponseResult unLike(@RequestBody  PostInfo postInfo) {
        return likeService.unLike( postInfo);
    }
}