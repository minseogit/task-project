package com.my.blog.app.search.controller;

import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.service.SearchBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchBlogController {

    private final SearchBlogService searchBlogService;

    @GetMapping("/blogs")
    public ResponseEntity<SearchBlogResponse> searchBlog(@Valid SearchBlogRequest request, ApiType apiType) {
        SearchBlogResponse response = searchBlogService.searchBlog(request, apiType);
        return ResponseEntity.ok(response);
    }
}
