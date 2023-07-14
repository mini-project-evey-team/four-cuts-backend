package com.team15.fourcuts.domain.post.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
