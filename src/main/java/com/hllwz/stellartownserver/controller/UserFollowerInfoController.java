package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.UserFollowerInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户关注控制器
 *
 * @author Lyh
 * @version 1.0.0
 */

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserFollowerInfoController {

    private final UserFollowerInfoService userFollowerInfoService;

    /**
     * 关注用户
     *
     * @return ResponseResult
     */
    @PostMapping("/followUser")
    public ResponseResult followUser(@RequestBody UserInfo userInfo) {
        return userFollowerInfoService.followUser(userInfo);
    }

    /**
     * 取消关注用户
     *
     * @return ResponseResult
     */
    @PostMapping("/unFollowUser")
    public ResponseResult unFollowUser(@RequestBody UserInfo userInfo){
        return  userFollowerInfoService.unFollowUser(userInfo);
    }
    /**
     * 返回关注用户
     *
     * @return ResponseResult
     */
    @GetMapping("/getUserFollow")
    public  ResponseResult getUserFollow(){
        return userFollowerInfoService.getUserFollow();
    }
}




