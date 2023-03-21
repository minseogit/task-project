package com.my.blog.app.search.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class SearchHistoryResponse {
    private long id;
    private String keyword;
    private int count;
}
