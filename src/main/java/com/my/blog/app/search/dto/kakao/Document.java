package com.my.blog.app.search.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Document {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;
}
