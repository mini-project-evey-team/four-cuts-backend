package com.team15.fourcuts.domain.post.controller;

import com.team15.fourcuts.domain.post.dto.PostRequestDto;
import com.team15.fourcuts.domain.post.dto.PostResponseDto;
import com.team15.fourcuts.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //게시글 목록조회(전체)
    @GetMapping("/post")
    public List<PostResponseDto> getPost(){
        return postService.getPost();
    }

    //게시글 목록조회(상세)
    @GetMapping("/post/{id}")
    public PostResponseDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping("/post")
    public void write(@RequestPart("post")PostRequestDto postRequestDto,
                      @RequestPart("photos") List<MultipartFile> photos) throws IOException {
        postService.write(postRequestDto, photos);
    }
}
