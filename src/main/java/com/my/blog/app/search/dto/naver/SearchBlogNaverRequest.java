package com.my.blog.app.search.dto.naver;

import com.my.blog.app.search.enums.SearchSortType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SearchBlogNaverRequest {
    @NotBlank
    private String query;

    private SearchSortType sort;

    private int display;

    private int start;
}
