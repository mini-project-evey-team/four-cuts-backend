package com.team15.fourcuts.domain.post.controller;

import com.team15.fourcuts.domain.post.dto.PostResponseDto;
import com.team15.fourcuts.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    //게시글 목록조회(전체)
    @GetMapping("")
    public List<PostResponseDto> getPost(){
        return postService.getPost();
    }

    //게시글 목록조회(상세)
    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }
}
