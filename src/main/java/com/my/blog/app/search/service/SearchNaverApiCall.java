package com.my.blog.app.search.service;

import com.my.blog.app.search.client.NaverSearchBlogClient;
import com.my.blog.app.search.converter.SearchDtoConverter;
import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.dto.naver.SearchBlogNaverResponse;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.enums.SearchSortType;
import com.my.blog.app.search.error.SearchErrorCode;
import com.my.blog.app.search.error.SearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchNaverApiCall implements SearchApiCall {

    private final NaverSearchBlogClient naverSearchBlogClient;

    @Override
    public SearchBlogResponse apiCall(SearchBlogRequest request) {
        SearchBlogNaverResponse response = naverSearchBlogClient.searchBlog(request.getQuery(), request.getPage(), request.getSize(), sortToNaver(request.getSort()));
        if(response == null) throw new SearchException(SearchErrorCode.API_CALL_ERROR);
        return SearchDtoConverter.convert(response);
    }

    @Override
    public ApiType getApiType() {
        return ApiType.Naver;
    }

    /**
     * sort Type을 Kakao->Naver에 맞게 변경
     */
    public static SearchSortType sortToNaver(SearchSortType sort) {
        switch (sort) {
            case accuracy:
                return SearchSortType.sim;
            case recency:
                return SearchSortType.date;
            default:
                return sort;
        }
    }

}
