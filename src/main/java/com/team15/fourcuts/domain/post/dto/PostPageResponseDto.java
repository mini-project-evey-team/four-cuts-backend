package com.team15.fourcuts.domain.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPageResponseDto {
    private final int total_page;
    private final int current_page;
    private final List<PostResponseDto> items;

    public PostPageResponseDto(int totalPages, int pageNum, List<PostResponseDto> list) {
        this.total_page = totalPages;
        this.current_page = pageNum;
        this.items = list;
    }
}
