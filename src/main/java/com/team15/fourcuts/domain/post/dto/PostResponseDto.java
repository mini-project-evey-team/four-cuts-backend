package com.team15.fourcuts.domain.post.dto;

import com.team15.fourcuts.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostResponseDto {
    private String username;
    private String title;
    private String content;
    private String photo_one;
    private String photo_two;
    private String photo_three;
    private String photo_four;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.photo_one = post.getPhoto_one();
        this.photo_two = post.getPhoto_two();
        this.photo_three = post.getPhoto_three();
        this.photo_four = post.getPhoto_four();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
