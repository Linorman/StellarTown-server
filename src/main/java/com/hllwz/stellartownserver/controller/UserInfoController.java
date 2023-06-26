package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.dto.LoginRequest;
import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.UserInfoService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息控制器
 * @author Linorman
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 注册
     * @param registerRequest 注册请求实体
     * @return ResponseResult
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterRequest registerRequest) {
        return userInfoService.register(registerRequest);
    }

    /**
     * 登录
     * @param loginRequest 登录请求实体
     * @return ResponseResult
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest loginRequest) {
        return userInfoService.login(loginRequest);
    }

    /**
     * 刷新token
     * @return ResponseResult
     */
    @GetMapping("/refreshToken")
    public ResponseResult refreshToken() {
        return userInfoService.refreshToken();
    }

    /**
     * 用户登出
     * @return ResponseResult
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        return userInfoService.logout();
    }

    /**
     * 获取当前登录用户信息
     * @return ResponseResult
     */
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo() {
        return userInfoService.getUserInfo();
    }

    /**
     * 更新用户信息
     * @param userInfo 用户信息实体
     * @return ResponseResult
     */
    @PutMapping("/updateUserInfo")
    public ResponseResult updateUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    /**
     * 根据id获取用户信息
     * @param id 用户id
     * @return ResponseResult
     */
    @GetMapping("/getUserInfoById/{id}")
    public ResponseResult getUserInfoById(@PathVariable Integer id) {
        return userInfoService.getUserInfoById(id);
    }
}
