package com.team15.fourcuts.domain.post.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team15.fourcuts.domain.post.dto.*;
import com.team15.fourcuts.domain.post.entity.Post;
import com.team15.fourcuts.domain.post.repository.MainPhotoRepository;
import com.team15.fourcuts.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final PostRepository postRepository;
    private final MainPhotoRepository mainPhotoRepository;

    //게시글 목록조회(전체)
    public PostPageResponseDto getPost(int pageNum) {

        // 팩토리 메서드를 통해 객체를 생성하는 이유에 대해 이채원님에게 설명해준다.
        Pageable pageable = PageRequest.of(pageNum - 1,6, Sort.by("modifiedAt"));
        Page<Post> posts = postRepository.findAll(pageable);

        // map에 대해서도 알아보고 설명해주기.
        return new PostPageResponseDto(posts.getTotalPages(), pageNum, posts.map(PostResponseDto::new).toList());
    }

    //게시글 목록조회(상세)
    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    //업로드
    public MessageResponseDto upload(PostRequestDto postRequestDto, List<MultipartFile> photos) throws IOException {
        validateFileCounts(photos);

        List<String> uuidFilePaths = new ArrayList<>();

        for (MultipartFile photo : photos) {
            long size = photo.getSize();

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(photo.getContentType());
            objectMetaData.setContentLength(size);

            String prefix = UUID.randomUUID().toString();
            String fileName = prefix + "_" + photo.getOriginalFilename();
            String bucketFilePath = "photos/" + fileName;

            // S3에 업로드
            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, bucketFilePath, photo.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            uuidFilePaths.add(fileName);
        }
        Post post = new Post(postRequestDto, uuidFilePaths);

        postRepository.save(post);

        return new MessageResponseDto("파일 저장 성공");

    }

    //findPost
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

        //validateFileExists
        private void validateFileCounts (List < MultipartFile > photos) {
            if (photos.isEmpty() || photos.size() != 4) {
                throw new IllegalArgumentException("이미지의 개수가 맞지 않습니다. 4개를 입력해주세요");
            }
    }

    public MainPhotoResponseDto getMainPhotos() {
        return new MainPhotoResponseDto(mainPhotoRepository.findAll());
    }
}
