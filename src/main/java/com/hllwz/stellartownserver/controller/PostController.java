package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子控制器
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/forum")
public class PostController {

    private final PostService postService;


    /**
     * 获取帖子
     *
     * @param id
     * @return ResponseResult
     */
    @GetMapping("/getPost")
    public ResponseResult getPost(Integer id) {
        return postService.getPost(id);
    }

    /**
     * 获取全部帖子
     *
     * @return ResponseResult
     */
    @GetMapping("/getAllPosts")
    public ResponseResult getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 发帖
     *
     * @param postInfo
     * @return ResponseResult
     */
    @PostMapping("/post")
    public ResponseResult post(@RequestBody PostInfo postInfo) {
        return postService.post(postInfo);
    }

    /**
     * 删帖
     *
     * @param postInfo
     * @return ResponseResult
     */
    @PostMapping("/deletePost")
    public ResponseResult deletePost(@RequestBody PostInfo postInfo) {
        return postService.deletePost(postInfo);
    }

    /**
     * 获取发布帖子
     *
     * @return ResponseResult
     */
    @GetMapping("/getUserPost")
    public ResponseResult getUserPost() {
        return postService.getUserPost();
    }

    /**
     * 获取他人发布帖子
     *
     * @param id
     * @return ResponseResult
     */
    @GetMapping("/getOthersPost")
    public ResponseResult getOthersPost(Integer id) {
        return postService.getOthersPost(id);
    }


}
