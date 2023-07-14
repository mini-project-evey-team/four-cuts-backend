package com.team15.fourcuts.domain.post.repository;

import com.team15.fourcuts.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
