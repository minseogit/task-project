package com.my.blog.app.search.error;

import com.my.blog.error.CommonException;
import lombok.Getter;

@Getter
public class SearchException extends CommonException {
    public SearchException(SearchErrorCode searchErrorCode) {
        super(searchErrorCode);
    }
}
