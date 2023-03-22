package com.my.blog.app.search.client;

import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.SearchSortType;
import com.my.blog.app.search.service.SearchNaverApiCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoSearchBlogClientFallbackFactory implements FallbackFactory<KakaoSearchBlogClient> {

    private final SearchNaverApiCall searchNaverApiCall;

    @Override
    public KakaoSearchBlogClient create(Throwable cause) {
        return new KakaoSearchBlogClient() {
            @Override
            public SearchBlogResponse searchBlog(String query, int page, int size, SearchSortType sort) {
                log.debug("** Kakao Api Call Fallback reason was: " + cause.getMessage());
                return searchNaverApiCall.apiCall(SearchBlogRequest.builder() //Kakao Api Call 실패시 Naver Api Call
                                                            .query(query)
                                                            .page(page)
                                                            .size(size)
                                                            .sort(sort)
                                                            .build());
            }
        };
    }
}
