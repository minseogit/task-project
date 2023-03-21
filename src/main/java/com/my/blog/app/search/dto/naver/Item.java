package com.my.blog.app.search.dto.naver;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Item {
    private String title;
    private String description;
    private String link;
    private String bloggername;
    private String postdate;
}
