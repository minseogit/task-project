package com.my.blog.app.search.dto.naver;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SearchBlogNaverResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
}
