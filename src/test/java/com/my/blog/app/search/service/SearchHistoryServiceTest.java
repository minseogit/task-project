package com.my.blog.app.search.service;

import com.my.blog.app.search.entity.SearchHistory;
import com.my.blog.app.search.error.SearchErrorCode;
import com.my.blog.app.search.error.SearchException;
import com.my.blog.app.search.repository.SearchHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SearchHistoryServiceTest {

    @InjectMocks
    private SearchHistoryService searchHistoryService;

    @Mock
    private SearchHistoryRepository searchRepository;

    @Test
    public void 인기검색어_조회_성공() {
        // given
        doReturn(Optional.of(createSearchHistoryList())).when(searchRepository).findTop10ByOrderByCountDesc();

        // when
        List<SearchHistory> response = searchHistoryService.searchPopularKeyword();

        // then
        verify(searchRepository, times(1)).findTop10ByOrderByCountDesc();
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    public void 인기검색어_조회_실패() {
        // given
        doReturn(Optional.empty()).when(searchRepository).findTop10ByOrderByCountDesc();

        // when
        SearchException result = assertThrows(SearchException.class, () -> searchHistoryService.searchPopularKeyword());

        // then
        assertThat(result.getErrorCode()).isEqualTo(SearchErrorCode.SEARCH_HISTORY_NOT_FOUND);
    }

    private List<SearchHistory> createSearchHistoryList() {
        SearchHistory searchHistory = SearchHistory.builder()
                .id(1L)
                .keyword("테스트")
                .count(1)
                .build();
        List<SearchHistory> list = new ArrayList<>();
        list.add(searchHistory);
        return list;
    }

}