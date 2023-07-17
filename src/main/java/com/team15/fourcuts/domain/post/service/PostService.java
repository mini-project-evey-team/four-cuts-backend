package com.team15.fourcuts.domain.post.service;

import com.team15.fourcuts.domain.post.dto.PostRequestDto;
import com.team15.fourcuts.domain.post.dto.PostResponseDto;
import com.team15.fourcuts.domain.post.entity.Post;
import com.team15.fourcuts.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ResourceLoader resourceLoader;

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

    public void write(PostRequestDto postRequestDto, List<MultipartFile> photos) throws IOException {
//        postRepository.save(new Post(postRequestDto, photos));

        // 파일을 실제로 저장하는 부분 read-only
        // 같은 서버인데 외부에 저장하는 거죠
        // 외부 서버(aws S3)에 저장하는거죠

        resourceLoader.getClassLoader()
                .getResourceAsStream("classpath:/static/picture/" + photos.get(0).getOriginalFilename());

        Path uploadPath = Paths.get("classpath:");
        File file = new File("/static/picture", photos.get(0).getOriginalFilename());
//        File file = new File(fileName);
        photos.get(0).transferTo(file);

        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getAbsolutePath());
//        Files.write( , photos.get(0).getBytes())

        // S3에 저장도 시켜줘야 한다.
    }
}
