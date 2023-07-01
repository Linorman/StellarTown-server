package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.service.impl.SkyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 天空转换Controller
 * @auther Linorman
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/skyTransfer")
public class SkyTransferController {

    private final SkyTransferService skyTransferService;

    /**
     * 天空转换
     * @param MultipartFile
     * @param String
     * @return ResponseEntity
     */
    @PostMapping("/transfer")
    public ResponseEntity skyTransfer(MultipartFile file, Integer maskId) {
        return skyTransferService.transfer(file, maskId);
    }
}
