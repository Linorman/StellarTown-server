package com.hllwz.stellartownserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息Mapper
 * @author Linorman
 * @version 1.0.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
