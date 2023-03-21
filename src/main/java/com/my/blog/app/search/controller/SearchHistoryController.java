package com.my.blog.app.search.controller;

import com.my.blog.app.search.entity.SearchHistory;
import com.my.blog.app.search.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    @GetMapping("/rank")
    public ResponseEntity<List<SearchHistory>> searchPopularKeyword() {
        List<SearchHistory> response = searchHistoryService.searchPopularKeyword();
        return ResponseEntity.ok(response);
    }
}