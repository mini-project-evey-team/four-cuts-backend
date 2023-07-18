package com.team15.fourcuts.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team15.fourcuts.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private List<String> photoUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.photoUrl = Arrays.asList(fileNameToURL(post.getPhoto_one()),
                fileNameToURL(post.getPhoto_two()),
                fileNameToURL(post.getPhoto_three()),
                fileNameToURL(post.getPhoto_four()));

        this.createdAt = post.getCreatedAt();
    }

    private String fileNameToURL(String fileName){
        return "https://chaewon-project-bucket.s3.ap-northeast-2.amazonaws.com/photos/" + fileName;
    }
}
