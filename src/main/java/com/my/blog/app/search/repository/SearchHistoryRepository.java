package com.my.blog.app.search.repository;

import com.my.blog.app.search.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    SearchHistory save(SearchHistory searchHistory);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SearchHistory> findByKeyword(String keyword);
    Optional<List<SearchHistory>> findTop10ByOrderByCountDesc();
}
