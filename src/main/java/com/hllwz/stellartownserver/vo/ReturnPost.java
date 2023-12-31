package com.hllwz.stellartownserver.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * 返回帖子响应类
 * @author Lyh
 * @version 1.0.0
 */
@Data
@RequiredArgsConstructor
public class ReturnPost implements Serializable {
    private int id;
    private int userId;
    private Integer likeCount;
    private String title;
    private String image;
    private String address;
    private String content;
    private String tag;
    private String shotTime;
    private Date postTime;
    private Double distance;
}