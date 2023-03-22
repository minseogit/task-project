package com.my.blog.app.search.service;

import com.my.blog.app.search.client.KakaoSearchBlogClient;
import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.ApiType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchKakaoApiCall implements SearchApiCall {
    private final KakaoSearchBlogClient kakaoSearchBlogClient;

    @Override
    public SearchBlogResponse apiCall(SearchBlogRequest request) {
        return kakaoSearchBlogClient.searchBlog(request.getQuery(), request.getPage(), request.getSize(), request.getSort());
    }

    @Override
    public ApiType getApiType() {
        return ApiType.Kakao;
    }

}
