package com.hllwz.stellartownserver.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 根据用户id获取用户信息响应类
 * @author Linorman
 * @version 1.0.0
 */
@Data
@RequiredArgsConstructor
public class OtherUserResponse implements Serializable {
    private Integer id;
    private String username;
    private String avatar;
    private String phoneNumber;
    private String address;
    private String signature;
    private int gender;
    private int age;
}
