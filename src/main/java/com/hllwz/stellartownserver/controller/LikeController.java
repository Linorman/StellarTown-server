package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/forum")
public class LikeController {

    private final LikeService likeService;

    /**
     * 点赞
     *
     * @param postInfo
     * @return ResponseResult
     */
    @PostMapping("/like")
    public ResponseResult like(@RequestBody PostInfo postInfo) {
        return likeService.like(postInfo);
    }

    /**
     * 取消点赞
     *
     * @param postInfo
     * @return ResponseResult
     */
    @PostMapping("/unLike")
    public ResponseResult unLike(@RequestBody PostInfo postInfo) {
        return likeService.unLike(postInfo);
    }
}