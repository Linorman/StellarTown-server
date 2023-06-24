package com.hllwz.stellartownserver.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostFollowerInfoMapper extends BaseMapper<PostFollowerInfo> {
    @Select("SELECT * FROM post_follower_info WHERE id = #{id}")
    PostFollowerInfo selectById(int id);
}