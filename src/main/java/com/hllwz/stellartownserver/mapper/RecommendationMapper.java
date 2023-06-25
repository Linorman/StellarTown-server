package com.hllwz.stellartownserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推荐信息Mapper
 * @author Lyh
 * @version 1.0.0
 */
@Mapper
public interface RecommendationMapper extends BaseMapper<PostFollowerInfo> {
}