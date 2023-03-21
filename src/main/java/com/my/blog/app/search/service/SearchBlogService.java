package com.my.blog.app.search.service;

import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.entity.SearchHistory;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.enums.factory.SearchBlogServiceFactory;
import com.my.blog.app.search.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchBlogService {

    private final SearchHistoryRepository searchRepository;
    private final SearchBlogServiceFactory searchBlogServiceFactory;

    @Transactional
    public SearchBlogResponse searchBlog(SearchBlogRequest request, ApiType apiType) {
        SearchBlogResponse response = searchBlogServiceFactory.getSearchApiCallType(apiType).apiCall(request);
        updateSearchHistory(request.getQuery());

        return response;
    }

    /**
     * 검색 이력 Update
     * @param query
     */
    private void updateSearchHistory(String query) {
        //키워드 검색 이력 조회해 없으면 새로 생성
        SearchHistory searchHistory = searchRepository.findByKeyword(query)
                .orElse(SearchHistory.builder()
                        .keyword(queryCheck(query))
                        .build());
        searchHistory.countUp();
        searchRepository.save(searchHistory);
    }

    /**
     * 질의어에서 블로그 url 분리
     * @param query
     */
    private String queryCheck(String query) {
        if(query.indexOf("http") > 0) query = query.substring(query.indexOf(" ")+1, query.length()); //검색어에 url이 있으면 제외하고 return
        return query;
    }
}
