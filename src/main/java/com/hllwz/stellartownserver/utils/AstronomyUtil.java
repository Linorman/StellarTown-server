package com.hllwz.stellartownserver.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hllwz.stellartownserver.entity.astronomyApi.MoonPhase;
import com.hllwz.stellartownserver.entity.astronomyApi.SpecialTime;
import com.hllwz.stellartownserver.entity.astronomyApi.TwilightData;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * 天文工具类
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
public class AstronomyUtil {
    private final static String API_KEY = "d418896d9b2c4f8080f6683620520ec6";
    private final static String MOON_PHASE_URL = "https://devapi.qweather.com/v7/astronomy/moon?key=" + API_KEY + "&location=";
    private final static String SPECIAL_TIME = "https://www.help.bj.cn/weather/api/nongli/moon/?id=";

    /**
     * 获取月相信息
     * @param cityCode
     * @return String
     */
    public static List<MoonPhase> getMoonPhase(String cityCode) {
        String url = MOON_PHASE_URL + cityCode;
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDateTime = currentDateTime.format(formatter);
        url += "&date=" + formattedDateTime;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("获取月相信息请求失败");
        }
        if (map == null) {
            log.error("获取月相信息为空");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String moonPhase = "";
        try {
            moonPhase = objectMapper.writeValueAsString(map.get("moonPhase"));
        } catch (Exception e) {
            log.error("JSON转换失败");
        }

        List<MoonPhase> moonPhaseList = new ArrayList<>();
        Gson gson = new Gson();
        MoonPhase[] moonPhases = gson.fromJson(moonPhase, MoonPhase[].class);
        Collections.addAll(moonPhaseList, moonPhases);
        return moonPhaseList;
    }

    /**
     * 获取蓝调时间、黄金时间
     * @param cityCode
     * @return Map<String, String>
     */
    public static SpecialTime getSpecialTime(String cityCode) {
        String url = SPECIAL_TIME + cityCode;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("获取蓝调时间、黄金时间请求失败");
        }
        if (map == null) {
            log.error("获取蓝调时间、黄金时间为空");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String specialTime = "";
        try {
            specialTime = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("JSON转换失败");
            return null;
        }
         SpecialTime specialTime1 = null;
        try {
            specialTime1 = objectMapper.readValue(specialTime, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JSON转换失败");
            return null;
        }
        return specialTime1;
    }

    /**
     * 根据经纬度获取曙暮光时间
     * @param lon
     * @param lat
     * @return String
     */
    public static TwilightData getTwilightTime(String lon, String lat) {
        String path = "src/main/resources/python/fetch.py";
        String[] args = new String[]{"python", path, lon, lat};
        String result = PythonUtil.execPython(path, args);
        ObjectMapper objectMapper = new ObjectMapper();
        TwilightData twilightData = null;
        try {
            twilightData = objectMapper.readValue(result, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        return twilightData;
    }

    /**
     * 根据城市名获取曙暮光时间
     * @param city
     * @return String
     */
    public static TwilightData getTwilightTime(String city) {
        Map<String, String> cityMap = CityUtil.getLocationByCity(city);
        if (cityMap == null) {
            log.error("城市名错误");
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        double formattedLatitude = Double.parseDouble(decimalFormat.format(Double.parseDouble(cityMap.get("lat"))));
        double formattedLongitude = Double.parseDouble(decimalFormat.format(Double.parseDouble(cityMap.get("lon"))));

        String path = "src/main/resources/python/fetch.py";
        String[] args = new String[]{"python", path, String.valueOf(formattedLongitude), String.valueOf(formattedLatitude)};
        String result = PythonUtil.execPython(path, args);
        ObjectMapper objectMapper = new ObjectMapper();
        TwilightData twilightData = null;
        try {
            twilightData = objectMapper.readValue(result, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        return twilightData;
    }

    /**
     * 发送GET请求
     * @param url
     * @return Map<String, Object>
     * @throws IOException
     */
    private static Map<String, Object> sendGetRequest(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.connect();
        String encoding = connection.getContentEncoding();
        BufferedReader reader;
        if (encoding != null && encoding.equals("gzip")) {
            reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        }
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        connection.disconnect();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(result.toString(), new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        return map;
    }
}
