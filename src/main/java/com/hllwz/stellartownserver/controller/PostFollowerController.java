package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.service.PostFollowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子点赞控制器
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class PostFollowerController {

    private final PostFollowerService postFollowerService;

    /**
     * 返回点赞数
     *
     * @param postFollowerInfo
     * @return ResponseResult
     */
    @GetMapping("/getLikes")
    public ResponseResult getLikes(@RequestBody PostFollowerInfo postFollowerInfo) {
        return postFollowerService.getLikes(postFollowerInfo);
    }

    /**
     * 返回点赞帖子
     *
     * @return ResponseResult
     */
    @GetMapping("/getLikedPosts")
    public ResponseResult getLikedPosts() {
        return postFollowerService.getLikedPosts();
    }
    /**
     * 返回他人点赞帖子
     *
     * @return ResponseResult
     */
@GetMapping("/getOthersLikedPosts")
public  ResponseResult getOthersLikedPosts(Integer id){return  postFollowerService.getOthersLikedPosts(id);}

    /**
     * 返回是否点赞
     *
     * @param postId
     * @return ResponseResult
     */
    @GetMapping("/isLiked")
    public ResponseResult isLiked(Integer postId) {
        return postFollowerService.isLiked(postId);
    }
}
