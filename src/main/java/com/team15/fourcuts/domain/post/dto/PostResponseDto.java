package com.team15.fourcuts.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team15.fourcuts.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private String photo_one;
    private String photo_two;
    private String photo_three;
    private String photo_four;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.photo_one = fileNameToURL(post.getPhoto_one());
        this.photo_two = fileNameToURL(post.getPhoto_two());
        this.photo_three = fileNameToURL(post.getPhoto_three());
        this.photo_four = fileNameToURL(post.getPhoto_four());
        this.createdAt = post.getCreatedAt();

        System.out.println("시간: " + this.createdAt);
    }

    private String fileNameToURL(String fileName){
        return "https://chaewon-project-bucket.s3.ap-northeast-2.amazonaws.com/photos/" + fileName;
    }
}
