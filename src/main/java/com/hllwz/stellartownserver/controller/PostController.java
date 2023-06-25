package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子控制器
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;

    @GetMapping("/getPost")
    /**
     * 获取帖子
     * @return ResponseResult
     */
    public ResponseResult getPost(@RequestBody PostInfo postInfo) {
        return postService.getPost(postInfo);
    }

    /**
     * 获取全部帖子
     * @return ResponseResult
     */
    @GetMapping("/getAllPosts")
    public ResponseResult getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 发帖
     * @return ResponseResult
     */
    @PostMapping("/post")
    public ResponseResult post(@RequestBody PostInfo postInfo) {
        return postService.post(postInfo);
    }

    /**
     * 删帖
     *
     * @return ResponseResult
     */
    @PostMapping("/deletePost")
    public ResponseResult deletePost(@RequestBody PostInfo postInfo) {
        return postService.deletePost(postInfo);
    }


}
