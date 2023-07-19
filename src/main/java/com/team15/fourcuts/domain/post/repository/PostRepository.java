package com.team15.fourcuts.domain.post.repository;

import com.team15.fourcuts.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //전체조회
    List<Post> findAllByOrderByModifiedAtDesc();
}
