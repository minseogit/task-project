package com.my.blog.app.search.client;

import com.my.blog.app.search.dto.naver.SearchBlogNaverResponse;
import com.my.blog.app.search.enums.SearchSortType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverSearchBlogClientFallbackFactory implements FallbackFactory<NaverSearchBlogClient> {

    @Override
    public NaverSearchBlogClient create(Throwable cause) {
        return new NaverSearchBlogClient() {
            @Override
            public SearchBlogNaverResponse searchBlog(String query, int page, int size, SearchSortType sort) {
                log.debug("** Naver Api Call Fallback reason was: " + cause.getMessage());
                return null;
            }
        };
    }
}
