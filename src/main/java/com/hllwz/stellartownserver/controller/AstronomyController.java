package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.AstronomyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 天文Controller
 * @auther Linorman
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/astronomy")
public class AstronomyController {

    private final AstronomyService astronomyService;

    /**
     * 根据城市名获取月相信息
     * @param cityName
     * @return responseResult
     */
    @GetMapping("/moon/cityName")
    public ResponseResult getAstronomyByCityName(String cityName) {
        return astronomyService.getAstronomyByCityName(cityName);
    }

    /**
     * 根据经纬度获取月相信息
     * @param lon
     * @param lat
     * @return responseResult
     */
    @GetMapping("/moon/location")
    public ResponseResult getAstronomyByLonLat(String lon, String lat) {
        return astronomyService.getAstronomyByLonLat(lon, lat);
    }

    /**
     * 根据城市名获取蓝调、黄金时间
     * @param cityName
     * @return responseResult
     */
    @GetMapping("/specialTime/cityName")
    public ResponseResult getBlueAndGoldenTimeByCity(String cityName) {
        return astronomyService.getBlueAndGoldenTimeByCityName(cityName);
    }

    /**
     * 根据经纬度获取蓝调、黄金时间
     * @param lon
     * @param lat
     * @return responseResult
     */
    @GetMapping("/specialTime/location")
    public ResponseResult getBlueAndGoldenTimeByLonLat(String lon, String lat) {
        return astronomyService.getBlueAndGoldenTimeByLonLat(lon, lat);
    }

    /**
     * 根据经纬度获取曙暮光时间
     * @param lon
     * @param lat
     * @return responseResult
     */
    @GetMapping("/twilight/location")
    public ResponseResult getTwilightByLonLat(String lon, String lat) {
        return astronomyService.getTwilightTime(lon, lat);
    }

    /**
     * 根据城市名获取曙暮光时间
     * @param cityName
     * @return responseResult
     */
    @GetMapping("/twilight/cityName")
    public ResponseResult getTwilightByCityName(String cityName) {
        return astronomyService.getTwilightTimeByCityName(cityName);
    }
}
