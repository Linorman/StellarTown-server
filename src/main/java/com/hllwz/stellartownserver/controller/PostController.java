package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.entity.PostInfo;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/forum")
public class PostController {
     @Autowired
     private PostService postService;

     @GetMapping("/getPost")
    public ResponseResult getPost(@RequestBody PostInfo postInfo){

         return postService.getPost(postInfo);
     }
     @GetMapping("/getAllPosts")
    public ResponseResult getAllPosts(){
         return postService.getAllPosts();
     }
     @PostMapping("/post")
    public ResponseResult post(@RequestBody PostInfo postInfo ){
         return  postService.post(postInfo);
     }
     @PostMapping("/deletePost")
    public ResponseResult deletePost(@RequestBody PostInfo postInfo ){
         return  postService.deletePost(postInfo);
     }




}
