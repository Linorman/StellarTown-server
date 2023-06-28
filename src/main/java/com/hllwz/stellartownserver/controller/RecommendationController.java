package com.hllwz.stellartownserver.controller;


import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 推荐帖子控制器
 *
 * @author Lyh
 * @version 1.0.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/forum")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    /**
     * 推荐帖子
     *
     * @return ResponseResult
     */
    @GetMapping("/recommendation")
    public ResponseResult getRecommendation() {
        return recommendationService.getRecommendation();

    }
}
