package com.team15.fourcuts.domain.post.entity;

import com.team15.fourcuts.domain.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")   // JPA 매핑할 테이블 이름
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String username;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private String photo_one;

    @Column(nullable = false)
    private String photo_two;

    @Column(nullable = false)
    private String photo_three;

    @Column(nullable = false)
    private String photo_four;

    @CurrentTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(updatable = false)
    LocalDateTime modifiedAt;

    public Post(PostRequestDto postRequestDto, List<String> uuidFileNames){
        this.username = postRequestDto.username();
        this.title = postRequestDto.title();
        this.content = postRequestDto.content();
        this.photo_one = uuidFileNames.get(0);
        this.photo_two = uuidFileNames.get(1);
        this.photo_three = uuidFileNames.get(2);
        this.photo_four = uuidFileNames.get(3);
    }
}
