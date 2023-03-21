package com.my.blog.app.search.dto.kakao;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SearchBlogResponse {
    private Meta meta;
    private List<Document> documents;
}
