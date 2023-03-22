package com.my.blog.app.search.client.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoSearchClientConfig {
    @Value("${client.kakao.auth}")
    private String auth;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("Authorization", "KakaoAK " + auth);
        };
    }
}
