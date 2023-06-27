package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.DonateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 捐赠控制器类
 * @author Linorman
 * @version 1.0.0
 */
@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonateController {

    private final DonateService donateService;

    @GetMapping("/info")
    public ResponseResult getDonateInfo() {
        return donateService.getDonateInfo();
    }
}
