package com.my.blog.app.search.repository;

import com.my.blog.app.search.entity.SearchHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class SearchHistoryRepositoryTest {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Test
    public void 검색내역_저장() {
        // given
        SearchHistory searchHistory = SearchHistory.builder()
                .id(1L)
                .keyword("test")
                .count(2)
                .build();

        // when
        SearchHistory response = searchHistoryRepository.save(searchHistory);

        // then
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void 검색내역_검색() {
        // given
        SearchHistory searchHistory = SearchHistory.builder()
                .id(1L)
                .keyword("test")
                .count(2)
                .build();
        searchHistoryRepository.save(searchHistory);

        // when
        Optional<SearchHistory> response = searchHistoryRepository.findByKeyword("test");

        // then
        assertThat(response).isNotNull();
        assertThat(response.get().getCount()).isEqualTo(2);
    }

    @Test
    public void 인기검색어_조회() {
        // given
        setHistoryData();

        // when
        Optional<List<SearchHistory>> response = searchHistoryRepository.findTop10ByOrderByCountDesc();

        // then
        assertThat(response).isNotNull();
        assertThat(response.get().get(0).getCount()).isGreaterThanOrEqualTo(response.get().get(1).getCount());
        assertThat(response.get().size()).isEqualTo(2);
    }

    private void setHistoryData() {
        SearchHistory searchHistory = SearchHistory.builder()
                .keyword("test")
                .count(1)
                .build();
        SearchHistory searchHistory2 = SearchHistory.builder()
                .keyword("test2")
                .count(5)
                .build();
        searchHistoryRepository.save(searchHistory);
        searchHistoryRepository.save(searchHistory2);
    }

}