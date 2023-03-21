package com.my.blog.app.search.converter;

import com.my.blog.app.search.dto.kakao.Document;
import com.my.blog.app.search.dto.kakao.Meta;
import com.my.blog.app.search.dto.kakao.SearchBlogResponse;
import com.my.blog.app.search.dto.naver.Item;
import com.my.blog.app.search.dto.naver.SearchBlogNaverResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchDtoConverter {

    public static SearchBlogResponse convert(SearchBlogNaverResponse response) {
        List<Item> items = response.getItems();
        List<Document> documents = new ArrayList<>();
        for(Item item : items) {
            Document document = Document.builder()
                    .title(item.getTitle())
                    .contents(item.getDescription())
                    .url(item.getLink())
                    .blogname(item.getBloggername())
                    .datetime(item.getPostdate())
                    .build();
            documents.add(document);
        }
        Meta meta = Meta.builder()
                .total_count(response.getTotal())
                .pageable_count(response.getDisplay())
                .build();

        return SearchBlogResponse.builder()
                .meta(meta)
                .documents(documents)
                .build();
    }

}
