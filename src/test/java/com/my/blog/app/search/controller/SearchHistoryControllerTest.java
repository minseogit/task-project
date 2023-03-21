package com.my.blog.app.search.controller;

import com.my.blog.app.search.entity.SearchHistory;
import com.my.blog.app.search.service.SearchHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchHistoryController.class)
class SearchHistoryControllerTest {

    @MockBean
    private SearchHistoryService searchHistoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 인기검색어_조회_성공() throws Exception {
        // given
        doReturn(createSearchHistoryList()).when(searchHistoryService).searchPopularKeyword();

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/search/rank"));

        // then
        resultActions.andExpect(status().isOk());
    }

    private List<SearchHistory> createSearchHistoryList() {
        SearchHistory searchHistory = SearchHistory.builder()
                .id(1L)
                .keyword("test")
                .count(2)
                .build();
        List<SearchHistory> searchHistories = new ArrayList<>();
        searchHistories.add(searchHistory);
        return searchHistories;
    }

}