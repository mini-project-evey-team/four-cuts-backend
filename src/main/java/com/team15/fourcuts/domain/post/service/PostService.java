package com.team15.fourcuts.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team15.fourcuts.domain.post.dto.MessageResponseDto;
import com.team15.fourcuts.domain.post.dto.PostRequestDto;
import com.team15.fourcuts.domain.post.dto.PostResponseDto;
import com.team15.fourcuts.domain.post.entity.Post;
import com.team15.fourcuts.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final AmazonS3Client amazonS3Client;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final PostRepository postRepository;

    //게시글 목록조회(전체)
    public List<PostResponseDto> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    //게시글 목록조회(상세)
    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    //업로드
    public MessageResponseDto upload(PostRequestDto postRequestDto, List<MultipartFile> photos) throws IOException {
        validateFileExists(photos);

        for (MultipartFile photo : photos) {
            String fileName = photo.getOriginalFilename();
            long size = photo.getSize();

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(photo.getContentType());
            objectMetaData.setContentLength(size);

            // S3에 업로드
            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, fileName, photo.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            String imagePath = amazonS3Client.getUrl(bucketName, fileName).toString(); // 접근가능한 URL 가져오기
        }
        Post post = new Post(postRequestDto, photos);

        Post savepost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savepost);

        return new MessageResponseDto("파일 저장 성공");

    }

    //findPost
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

        //validateFileExists
        private void validateFileExists (List < MultipartFile > photos) {
            if (photos.isEmpty()) {
                throw new IllegalArgumentException("EmptyFile");
        }
    }
}
