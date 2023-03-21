package com.my.blog.app.search.controller;

import com.my.blog.app.search.dto.kakao.Document;
import com.my.blog.app.search.dto.kakao.Meta;
import com.my.blog.app.search.dto.kakao.SearchBlogRequest;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.enums.ApiType;
import com.my.blog.app.search.enums.SearchSortType;
import com.my.blog.app.search.service.SearchBlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchBlogController.class)
class SearchBlogControllerTest {

    @MockBean
    private SearchBlogService searchBlogService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 블로그검색_성공() throws Exception {
        // given
        doReturn(createSearchBlogResponse()).when(searchBlogService)
                .searchBlog(any(SearchBlogRequest.class), any(ApiType.class));

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/search/blogs")
                        .param("query", "test")
                        .param("sort", String.valueOf(SearchSortType.accuracy)));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void 블로그검색_Query_파라미터오류() throws Exception {
        // given
        doReturn(createSearchBlogResponse()).when(searchBlogService)
                .searchBlog(any(SearchBlogRequest.class), any(ApiType.class));

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/search/blogs")
                        .param("query", ""));

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void 블로그검색_SORT_파라미터오류() throws Exception {
        doReturn(createSearchBlogResponse()).when(searchBlogService)
                .searchBlog(any(SearchBlogRequest.class), any(ApiType.class));

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/search/blogs")
                        .param("query", "test")
                        .param("sort", "LINE"));

        resultActions.andExpect(status().isBadRequest());
    }

    private SearchBlogResponse createSearchBlogResponse() {
        Meta meta = Meta.builder()
                .total_count(10)
                .pageable_count(5)
                .build();
        Document document = Document.builder()
                .url("https://test.com")
                .blogname("Test")
                .title("test")
                .contents("test")
                .build();
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        return SearchBlogResponse.builder()
                .meta(meta)
                .documents(documents)
                .build();
    }

}