package com.hllwz.stellartownserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 注册请求实体
 * @author Linorman
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    private String username;
    private String password;
    private String phoneNumber;
}
