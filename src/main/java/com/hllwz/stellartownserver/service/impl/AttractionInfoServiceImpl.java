package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.AttractionInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.AttractionInfoMapper;
import com.hllwz.stellartownserver.service.AttractionInfoService;
import com.hllwz.stellartownserver.utils.CityUtil;
import com.hllwz.stellartownserver.utils.PinYinUtil;
import com.hllwz.stellartownserver.utils.RecommendUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.ReturnAttraction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 推荐景点Service实现类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_stellar_town_attraction")
public class AttractionInfoServiceImpl extends ServiceImpl<AttractionInfoMapper, AttractionInfo> implements AttractionInfoService {

    private final AttractionInfoMapper attractionInfoMapper;

    private final RecommendUtil recommendUtil;


    @Override
    public ResponseResult getAttraction(UserInfo userInfo) {
        List<AttractionInfo> attractionInfo = attractionInfoMapper.selectList(null);
        List<ReturnAttraction> attractionInfo3 = new ArrayList<>();
        for (AttractionInfo attractionInfo1 : attractionInfo) {
            ReturnAttraction attractionInfo2 = new ReturnAttraction();
            attractionInfo2.setAddress(attractionInfo1.getAddress());
            attractionInfo2.setId(attractionInfo1.getId());
            attractionInfo2.setImage(attractionInfo1.getImage());
            attractionInfo2.setAltitude(attractionInfo1.getAltitude());
            attractionInfo2.setIntroduction(attractionInfo1.getIntroduction());
            attractionInfo2.setLatitude(attractionInfo1.getLatitude());
            attractionInfo2.setLongitude(attractionInfo1.getLongitude());
            if (attractionInfo1 != null) {
                attractionInfo3.add(attractionInfo2);
            }
        }//输出vo

        for (ReturnAttraction attractionInfo11 : attractionInfo3) {
            double longitude = Double.parseDouble(attractionInfo11.getLongitude());
            double latitude = Double.parseDouble(attractionInfo11.getLatitude());
            Map<String, String> location1 = CityUtil.getLocationByCity(PinYinUtil.toPinyin(userInfo.getAddress()));
            double userLat = Double.parseDouble(location1.get("lat"));
            double userLon = Double.parseDouble(location1.get("lon"));
            double distance = recommendUtil.calculateDistance(userLat, userLon, latitude,longitude);
            attractionInfo11.setDistance(distance);
        }
        Collections.sort(attractionInfo3, Comparator.comparing(ReturnAttraction::getDistance));
        return ResponseResult.success(ResultCode.ATTRACTION_GET_SUCCESS, attractionInfo3);

    }
}