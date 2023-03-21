package com.my.blog.app.search.service;

import com.my.blog.app.search.entity.SearchHistory;
import com.my.blog.app.search.error.SearchErrorCode;
import com.my.blog.app.search.error.SearchException;
import com.my.blog.app.search.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchRepository;

    public List<SearchHistory> searchPopularKeyword() {
        List<SearchHistory> response = searchRepository.findTop10ByOrderByCountDesc()
                .orElseThrow(() -> new SearchException(SearchErrorCode.SEARCH_HISTORY_NOT_FOUND));
        return response;
    }
}
