package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.service.PostFollowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/like")
public class PostFollowerController {
    @Autowired
    private PostFollowerService postFollowerService;

    /**
     * 返回点赞数
     * @return ResponseResult
     */
    @GetMapping("/getLikes")
    public ResponseResult getLikes(@RequestBody PostFollowerInfo postFollowerInfo) {
        return postFollowerService.getLikes(postFollowerInfo);
    }


}
