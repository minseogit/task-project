package com.my.blog.app.search.dto.kakao;

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
public class SearchBlogRequest {
    @NotBlank
    private String query;

    private SearchSortType sort;

    private int page;

    private int size;
}
