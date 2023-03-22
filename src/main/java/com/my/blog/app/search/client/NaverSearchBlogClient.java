package com.my.blog.app.search.client;

import com.my.blog.app.search.client.config.NaverSearchClientConfig;
import com.my.blog.app.search.enums.SearchSortType;
import com.my.blog.app.search.dto.naver.SearchBlogNaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        url = "${client.naver.url}",
        name = "NaverSearchBlogClient",
        configuration= NaverSearchClientConfig.class,
        fallbackFactory = NaverSearchBlogClientFallbackFactory.class
)
public interface NaverSearchBlogClient {

    @GetMapping(value = "${client.naver.path}")
    SearchBlogNaverResponse searchBlog(@RequestParam("query") String query,
                                       @RequestParam(value = "start", defaultValue = "1") int start,
                                       @RequestParam(value = "display", defaultValue = "10") int display,
                                       @RequestParam(value = "sort", defaultValue = "sim") SearchSortType sort);
}