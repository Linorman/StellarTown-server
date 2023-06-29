package com.hllwz.stellartownserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hllwz.stellartownserver.entity.weatherApi.DailyData;
import com.hllwz.stellartownserver.entity.weatherApi.NowData;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * 天气工具类
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
public class WeatherUtil {

    private final static String WEATHER_API_KEY = "d418896d9b2c4f8080f6683620520ec6";
    private final static String NOW_WEATHER_URL = "https://devapi.qweather.com/v7/weather/now?key=" + WEATHER_API_KEY + "&location=";
    private final static String SEVEN_DAY_WEATHER_URL = "https://devapi.qweather.com/v7/weather/7d?key=" + WEATHER_API_KEY + "&location=";

    /**
     * 获取实时天气信息
     * @param cityCode
     * @return String
     */
    public static NowData getNowWeather(String cityCode) {

        String url = NOW_WEATHER_URL + cityCode;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("获取1day天气信息请求失败");
        }
        if (map == null) {
            log.error("获取1day天气信息为空");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String now = "";
        try {
            now = objectMapper.writeValueAsString(map.get("now"));
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        NowData nowData = null;
        try {
            nowData = objectMapper.readValue(now, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        return nowData;
    }

    /**
     * 获取未来七天天气信息
     * @param cityCode
     * @return List<DailyData>
     */
    public static List<DailyData> getSevenDayWeather(String cityCode) {

        String url = SEVEN_DAY_WEATHER_URL + cityCode;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("获取7day天气信息请求失败");
        }
        if (map == null) {
            log.error("获取7day天气信息为空");
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String data = "";
        try {
            data = objectMapper.writeValueAsString(map.get("daily"));
        } catch (JsonProcessingException e) {
            log.error("JSON转换失败");
        }


        List<DailyData> dailyDataList = new ArrayList<>();
        Gson gson = new Gson();
        DailyData[] dailyDataArray = gson.fromJson(data, DailyData[].class);
        Collections.addAll(dailyDataList, dailyDataArray);

        return dailyDataList;
    }

    /**
     * 发送GET请求
     * @param url
     * @return Map<String, Object>
     */
    public static Map<String, Object> sendGetRequest(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept-Encoding", "gzip");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            return null;
        }

        BufferedReader in;
        if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), StandardCharsets.UTF_8));
        } else {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(response.toString(), new TypeReference<>() {
            });

        } catch (IOException e) {
            log.error("JSON转换失败");
        }
        return map;
    }
}
