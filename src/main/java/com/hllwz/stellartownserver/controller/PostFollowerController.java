package com.hllwz.stellartownserver.controller;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostFollowerInfo;
import com.hllwz.stellartownserver.service.PostFollowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/like")
public class PostFollowerController {
    @Autowired
    private PostFollowerService postFollowerService;

    @GetMapping("/getLikes")
    public ResponseResult getLikes(@RequestBody PostFollowerInfo postFollowerInfo){
        return  postFollowerService.getLikes(postFollowerInfo);
    }


}
