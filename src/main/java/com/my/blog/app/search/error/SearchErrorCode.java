package com.my.blog.app.search.error;

import com.my.blog.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SearchErrorCode implements ErrorCode {

    SEARCH_HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "검색 결과가 존재하지 않습니다."),
    INVALID_SEARCH_API_TYPE(HttpStatus.NOT_FOUND, "API TYPE이 유효하지 않습니다."),
    API_CALL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출에 실패했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
