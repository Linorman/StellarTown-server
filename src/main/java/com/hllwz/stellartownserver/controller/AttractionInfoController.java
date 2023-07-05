package com.hllwz.stellartownserver.controller;


import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.AttractionInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 景点控制器
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/attraction")
public class AttractionInfoController {

    private final AttractionInfoService attractionInfoService;

    @GetMapping("/getAttraction")
    public ResponseResult getAttraction(String address) {
        return attractionInfoService.getAttraction(address);
    }
}

