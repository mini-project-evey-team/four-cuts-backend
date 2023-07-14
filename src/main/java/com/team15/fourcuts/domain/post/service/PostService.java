package com.team15.fourcuts.domain.post.service;

import com.team15.fourcuts.domain.post.dto.PostResponseDto;
import com.team15.fourcuts.domain.post.entity.Post;
import com.team15.fourcuts.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //게시글 목록조회(전체)
    public List<PostResponseDto> getPost(){
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    //게시글 목록조회(상세)
    public PostResponseDto getPostById(Long id){
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    //findPost
    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
