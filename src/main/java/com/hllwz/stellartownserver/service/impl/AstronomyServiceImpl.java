package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.astronomyApi.MoonPhase;
import com.hllwz.stellartownserver.entity.astronomyApi.SpecialTime;
import com.hllwz.stellartownserver.entity.astronomyApi.TwilightData;
import com.hllwz.stellartownserver.service.AstronomyService;
import com.hllwz.stellartownserver.utils.AstronomyUtil;
import com.hllwz.stellartownserver.utils.CityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 天文Service实现类
 * @author  Linorman
 * @version 1.0.0
 */
@Service
@Slf4j
public class AstronomyServiceImpl implements AstronomyService {
    @Override
    public ResponseResult getAstronomyByCityName(String cityName) {
        String cityCode = CityUtil.getCityCodeByCity(cityName);
        List<MoonPhase> moonPhaseList = null;
        try {
            moonPhaseList = AstronomyUtil.getMoonPhase(cityCode);
        } catch (Exception e) {
            log.error("获取天文信息失败");
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        }
        if (moonPhaseList == null) {
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        } else {
            return ResponseResult.success(ResultCode.ASTRONOMY_GET_SUCCESS, moonPhaseList);
        }
    }

    @Override
    public ResponseResult getAstronomyByLonLat(String lon, String lat) {
        String cityCode = CityUtil.getCityCodeByLocation(lon, lat);
        List<MoonPhase> moonPhaseList = null;
        try {
            moonPhaseList = AstronomyUtil.getMoonPhase(cityCode);
        } catch (Exception e) {
            log.error("获取天文信息失败");
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        }
        if (moonPhaseList == null) {
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        } else {
            return ResponseResult.success(ResultCode.ASTRONOMY_GET_SUCCESS, moonPhaseList);
        }
    }

    @Override
    public ResponseResult getBlueAndGoldenTimeByCityName(String cityName) {
        String cityCode = CityUtil.getCityCodeByCity(cityName);
        SpecialTime blueAndGoldenTime = null;
        try {
            blueAndGoldenTime = AstronomyUtil.getSpecialTime(cityCode);
        } catch (Exception e) {
            log.error("获取天文信息失败");
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        }
        if (blueAndGoldenTime == null) {
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        } else {
            return ResponseResult.success(ResultCode.ASTRONOMY_GET_SUCCESS, blueAndGoldenTime);
        }
    }

    @Override
    public ResponseResult getBlueAndGoldenTimeByLonLat(String lon, String lat) {
        String cityCode = CityUtil.getCityCodeByLocation(lon, lat);
        SpecialTime blueAndGoldenTime = null;
        try {
            blueAndGoldenTime = AstronomyUtil.getSpecialTime(cityCode);
        } catch (Exception e) {
            log.error("获取天文信息失败");
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        }
        if (blueAndGoldenTime == null) {
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        } else {
            return ResponseResult.success(ResultCode.ASTRONOMY_GET_SUCCESS, blueAndGoldenTime);
        }
    }

    @Override
    public ResponseResult getTwilightTime(String lon, String lat) {
        TwilightData twilightData = null;
        try {
            twilightData = AstronomyUtil.getTwilightTime(lon, lat);
        } catch (Exception e) {
            log.error("获取天文信息失败");
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        }
        if (twilightData == null) {
            return ResponseResult.error(ResultCode.ASTRONOMY_GET_ERROR, null);
        } else {
            return ResponseResult.success(ResultCode.ASTRONOMY_GET_SUCCESS, twilightData);
        }
    }
}
