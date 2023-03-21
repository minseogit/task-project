package com.my.blog.app.search.service;

import com.my.blog.app.search.client.KakaoSearchBlogClient;
import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.enums.SearchSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchKakaoApiCall implements SearchApiCall {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final KakaoSearchBlogClient kakaoSearchBlogClient;
    private final SearchNaverApiCall searchNaverApiCall;

    @Override
    public SearchBlogResponse apiCall(SearchBlogRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreakerKakao");
        return circuitBreaker.run(() -> kakaoSearchBlogClient.searchBlog(request.getQuery(), request.getPage(), request.getSize(), request.getSort()),
                throwable -> {
                    return searchNaverApiCall.apiCall(request); //Exception 발생시 Naver Api Call
                });
    }

    @Override
    public ApiType getApiType() {
        return ApiType.Kakao;
    }

    /**
     * Kakao 검색 Api 파라미터 값이 존재하지 않으면 기본값으로 set
     */
    public SearchBlogRequest setKakaoRequestDefaultValue(SearchBlogRequest request) {
        if(request.getSort() == null) request.setSort(SearchSortType.accuracy);
        if(request.getPage() == 0) request.setPage(1);
        if(request.getSize() == 0) request.setSize(10);
        return request;
    }
}
