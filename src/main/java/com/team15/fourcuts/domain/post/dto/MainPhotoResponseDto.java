package com.team15.fourcuts.domain.post.dto;

import com.team15.fourcuts.domain.post.entity.MainPhotos;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MainPhotoResponseDto {
    private final List<String> photoUrls;

    public MainPhotoResponseDto(List<MainPhotos> mainPhotos){
        this.photoUrls = new ArrayList<>();

        for(MainPhotos mainPhoto : mainPhotos){
            photoUrls.add(fileNameToURL(mainPhoto.getPhotoUrl()));
        }
    }

    private String fileNameToURL(String fileName){
        return "https://chaewon-project-bucket.s3.ap-northeast-2.amazonaws.com/mainPhoto/" + fileName;
    }
}
