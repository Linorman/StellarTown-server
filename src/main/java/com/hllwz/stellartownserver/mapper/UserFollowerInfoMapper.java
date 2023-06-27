package com.hllwz.stellartownserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.UserFollowerInfo;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户关注信息Mapper
 * @author Linorman
 * @version 1.0.0
 */

@Mapper
public interface UserFollowerInfoMapper extends BaseMapper<UserFollowerInfo> {
}

