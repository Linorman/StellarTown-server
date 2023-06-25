package com.hllwz.stellartownserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录请求类
 * @author Linorman
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    private String phoneNumber;
}
