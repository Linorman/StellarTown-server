package com.hllwz.stellartownserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子信息实体类
 *
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private int userId;
    private Integer likeCount ;
    private String title;
    private String image;
    private String address;
    private String content;
    private String shotTime;
    private String tag;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "post_time", fill = FieldFill.INSERT)
    private Date postTime;
    private int delFlag;
}
