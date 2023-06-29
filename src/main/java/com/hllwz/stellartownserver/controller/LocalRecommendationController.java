package com.hllwz.stellartownserver.controller;


import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.LocalRecommendationService;
import com.hllwz.stellartownserver.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推荐帖子控制器
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forum")
public class LocalRecommendationController {
    private final LocalRecommendationService localRecommendationService;

    /**
     * 推荐帖子
     *
     * @return ResponseResult
     */
    @GetMapping("/localRecommendation")
    public ResponseResult getLocalRecommendation() {
        return localRecommendationService.getLocalRecommendation();

    }
}
