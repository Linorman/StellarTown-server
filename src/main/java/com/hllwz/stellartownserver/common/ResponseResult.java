package com.hllwz.stellartownserver.common;

import lombok.Data;

import java.util.HashMap;

/**
 * 结果通用类
 * @author linorman
 * @version 1.0.1
 */
@Data
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;
    private HashMap<String, Object> map;

    public static <T> ResponseResult<T> success(ResultCode resultCode, T data) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode((int) resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> ResponseResult<T> error(ResultCode resultCode, T data) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode((int) resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> ResponseResult<T> success(ResultCode resultCode, HashMap<String, Object> map) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode((int) resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setMap(map);
        return r;
    }
}
