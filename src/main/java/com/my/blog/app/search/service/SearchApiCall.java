package com.my.blog.app.search.service;

import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;

public interface SearchApiCall {
    SearchBlogResponse apiCall(SearchBlogRequest request);
    ApiType getApiType();
}
