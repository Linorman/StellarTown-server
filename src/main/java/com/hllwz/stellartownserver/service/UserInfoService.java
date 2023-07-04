package com.hllwz.stellartownserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.dto.LoginRequest;
import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.entity.UserInfo;

/**
 * 用户信息Service接口
 * @author Linorman
 * @version 1.0.0
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 用户注册
     * @param registerRequest
     * @return ResponseResult
     */
    public ResponseResult register(RegisterRequest registerRequest);

    /**
     * 用户登录
     * @param loginRequest
     * @return ResponseResult
     */
    public ResponseResult login(LoginRequest loginRequest);

    /**
     * 用户刷新token
     * @return ResponseResult
     */
    public ResponseResult refreshToken();

    /**
     * 用户注销
     * @return ResponseResult
     */
    public ResponseResult logout();

    /**
     * 获取当前登录用户信息
     * @return ResponseResult
     */
    public ResponseResult getUserInfo();

    /**
     * 用户更新信息
     * @param userInfo
     * @return ResponseResult
     */
    @Deprecated
    public ResponseResult updateUserInfo(UserInfo userInfo);

    /**
     * 更新用户地址
     * @param address
     * @return ResponseResult
     */
    public ResponseResult updateUserAddress(String address);

    /**
     * 更新用户个性签名
     * @param signature
     * @return ResponseResult
     */
    public ResponseResult updateUserSignature(String signature);

    /**
     * 根据id获取用户信息
     * @param id
     * @return ResponseResult
     */
    public ResponseResult getUserInfoById(Integer id);
}
