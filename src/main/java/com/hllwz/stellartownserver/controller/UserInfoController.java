package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.dto.LoginRequest;
import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息控制器
 *
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
     *
     * @param registerRequest 注册请求实体
     * @return ResponseResult
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterRequest registerRequest) {
        return userInfoService.register(registerRequest);
    }

    /**
     * 登录
     *
     * @param loginRequest 登录请求实体
     * @return ResponseResult
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest loginRequest) {
        return userInfoService.login(loginRequest);
    }

    /**
     * 刷新token
     *
     * @return ResponseResult
     */
    @GetMapping("/refreshToken")
    public ResponseResult refreshToken() {
        return userInfoService.refreshToken();
    }

    /**
     * 用户登出
     *
     * @return ResponseResult
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        return userInfoService.logout();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return ResponseResult
     */
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo() {
        return userInfoService.getUserInfo();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息实体
     * @return ResponseResult
     */
    @PutMapping("/updateUserInfo")
    @Deprecated
    public ResponseResult updateUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    /**
     * 根据id获取用户信息
     *
     * @param userInfo 用户id
     * @return ResponseResult
     */
    @GetMapping("/getUserInfoById")
    public ResponseResult getUserInfoById(Integer id) {
        return userInfoService.getUserInfoById(id);
    }

    /**
     * 更新用户地址
     * @param address
     * @return ResponseResult
     */
    @PutMapping("/updateUserAddress")
    public ResponseResult updateUserAddress(@RequestParam String address) {
        return userInfoService.updateUserAddress(address);
    }

    /**
     * 更新用户个性签名
     * @param signature
     * @return ResponseResult
     */
    @PutMapping("/updateUserSignature")
    public ResponseResult updateUserSignature(@RequestParam String signature) {
        return userInfoService.updateUserSignature(signature);
    }

    /**
     * 更新用户名
     * @param username
     * @return ResponseResult
     */
    @PutMapping("/updateUsername")
    public ResponseResult updateUsername(@RequestParam String username) {
        return userInfoService.updateUsername(username);
    }

    /**
     * 更新用户性别
     * @param gender
     * @return ResponseResult
     */
    @PutMapping("/updateGender")
    public ResponseResult updateGender(@RequestParam Integer gender) {
        return userInfoService.updateGender(gender);
    }

    /**
     * 更新用户年龄
     * @param age
     * @return ResponseResult
     */
    @PutMapping("/updateAge")
    public ResponseResult updateAge(@RequestParam Integer age) {
        return userInfoService.updateAge(age);
    }
}
