package com.my.blog.app.search.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Meta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
