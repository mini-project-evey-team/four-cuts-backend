package com.team15.fourcuts.domain.post.repository;

import com.team15.fourcuts.domain.post.entity.MainPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainPhotoRepository extends JpaRepository<MainPhotos, Long> {
}
