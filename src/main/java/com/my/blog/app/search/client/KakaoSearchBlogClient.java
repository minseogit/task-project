package com.my.blog.app.search.client;

import com.my.blog.app.search.client.config.KakaoSearchClientConfig;
import com.my.blog.app.search.enums.SearchSortType;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        url = "${client.kakao.url}",
        name = "KakaoSearchBlogClient",
        configuration= KakaoSearchClientConfig.class,
        fallbackFactory = KakaoSearchBlogClientFallbackFactory.class
)
public interface KakaoSearchBlogClient {

    @GetMapping(value = "${client.kakao.path}")
    SearchBlogResponse searchBlog(@RequestParam("query") String query,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  @RequestParam(value = "sort", defaultValue = "accuracy") SearchSortType sort);
}
