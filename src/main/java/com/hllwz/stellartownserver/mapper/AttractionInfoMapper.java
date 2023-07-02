package com.hllwz.stellartownserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hllwz.stellartownserver.entity.AttractionInfo;
import com.hllwz.stellartownserver.entity.PostInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 景点信息Mapper
 *
 * @author Lyh
 * @version 1.0.0
 */
@Mapper
public interface AttractionInfoMapper extends BaseMapper<AttractionInfo> {
}