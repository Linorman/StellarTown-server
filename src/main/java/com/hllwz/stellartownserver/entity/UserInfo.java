package com.hllwz.stellartownserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 * @author Linorman
 * @version 1.0.0
 */
@Data
public class UserInfo implements Serializable {
    @TableId(value = "id")
    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String address;
    private String signature;
    private int gender;
    private int age;
    private int delFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
