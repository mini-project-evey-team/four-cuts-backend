package com.team15.fourcuts.domain.post.controller;

import com.team15.fourcuts.domain.post.dto.*;
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

    @GetMapping("/main/photo")
    public MainPhotoResponseDto getMainPhotos(){
        return postService.getMainPhotos();
    }

    @GetMapping("/post")
    public PostPageResponseDto getPost(@RequestParam(name="page", defaultValue = "1") int pageNum){
        return postService.getPost(pageNum);
    }

    //게시글 목록조회(상세)
    @GetMapping("/post/{id}")
    public PostResponseDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    //업로드
    @PostMapping("/post")
    public MessageResponseDto uploadFile(
            @RequestPart("post") PostRequestDto postRequestDto,
            @RequestPart("photos") List<MultipartFile> photos) throws IOException {
        return postService.upload(postRequestDto, photos);
    }
}
