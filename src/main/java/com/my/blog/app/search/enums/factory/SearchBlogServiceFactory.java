package com.my.blog.app.search.enums.factory;

import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.service.SearchApiCall;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SearchBlogServiceFactory {

    private static final Map<ApiType, SearchApiCall> searchApiCallHashMap = new HashMap<>();

    /**
     * 생성자주입
     * @param searchApiCalls
     */
    public SearchBlogServiceFactory(List<SearchApiCall> searchApiCalls) {
        searchApiCalls.forEach(s -> searchApiCallHashMap.put(s.getApiType(), s));
    }

    public static SearchApiCall getSearchApiCallType(ApiType apiType) {
        return searchApiCallHashMap.get(apiType);
    }
}
