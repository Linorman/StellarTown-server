package com.hllwz.stellartownserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.PostInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 帖子信息Mapper
 * @author Lyh
 * @version 1.0.0
 */
@Mapper
public interface PostInfoMapper extends BaseMapper<PostInfo> {
    @Select("SELECT * FROM ${tableName}")
    List<PostInfo> selectPostInfo(@Param("tableName") String tableName);

    @Insert("INSERT INTO post_info (user_id,image,title,content)VALUES(#{userId},#{image},#{title},#{content})")
    int insert(PostInfo postInfo);

    @Select("SELECT * FROM post_info WHERE id = #{id}")
    PostInfo selectById(int id);
}


