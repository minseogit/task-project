package com.my.blog.app.search.service;

import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.enums.SearchSortType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SearchBlogServiceTest {

    @Autowired
    SearchBlogService searchBlogService;
    @Autowired
    SearchBlogServiceFactory searchBlogServiceFactory;


    @Test
    public void 블로그검색_Kakao_성공() {
        SearchBlogRequest searchBlogRequest = createSearchBlogRequest();
        SearchBlogResponse response = searchBlogService.searchBlog(searchBlogRequest, ApiType.Kakao);

        assertThat(response.getDocuments().get(0).getThumbnail()).isNotNull(); //Kakao는 Response에 썸네일 존재 O
    }

    @Test
    public void 블로그검색_Naver_성공() {
        SearchBlogRequest searchBlogRequest = createSearchBlogRequest();
        SearchBlogResponse response = searchBlogService.searchBlog(searchBlogRequest, ApiType.Naver);

        assertThat(response.getDocuments().get(0).getThumbnail()).isNull(); //Naver는 Response에 썸네일 존재 X
    }

    @Test
    void 블로그검색_Kakao_실패() {
        SearchBlogRequest searchBlogRequest = createErrorSearchBlogRequest(); //Kakao는 page의 max가 50, naver는 100이므로 50~100 사이 입력시 에러 발생
        SearchBlogResponse response = searchBlogService.searchBlog(searchBlogRequest, ApiType.Kakao);

        assertThat(response).isNotNull();
        assertThat(response.getDocuments().get(0).getThumbnail()).isNull(); //에러 발생시 네이버 API가 호출되어 썸네일이 존재하지않음.

    }

    private SearchBlogRequest createSearchBlogRequest() {
        return SearchBlogRequest.builder()
                .query("테스트")
                .page(1)
                .size(10)
                .sort(SearchSortType.accuracy)
                .build();
    }

    private SearchBlogRequest createErrorSearchBlogRequest() {
        return SearchBlogRequest.builder()
                .query("테스트")
                .page(1)
                .size(55)
                .sort(SearchSortType.accuracy)
                .build();
    }

}