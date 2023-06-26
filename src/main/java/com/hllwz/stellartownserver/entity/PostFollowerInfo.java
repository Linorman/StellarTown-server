package com.hllwz.stellartownserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子点赞实体类
 *
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFollowerInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer postId;
    private Integer followerId;
    private int delFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "follow_time", fill = FieldFill.INSERT)
    private Date followTime;
}
