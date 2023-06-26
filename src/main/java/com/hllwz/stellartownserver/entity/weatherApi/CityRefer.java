package com.hllwz.stellartownserver.entity.weatherApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 天气API信息实体类-CityRefer
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class CityRefer {
    private List<String> sources;
    private List<String> license;

    // 构造方法、Getter和Setter省略

    // 序列化方法
    public String serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 反序列化方法
    public static CityRefer deserialize(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, CityRefer.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}