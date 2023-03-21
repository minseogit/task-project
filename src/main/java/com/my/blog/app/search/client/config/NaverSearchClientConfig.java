package com.my.blog.app.search.client.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverSearchClientConfig {
    @Value("${client.naver.client-id}")
    private String clientId;

    @Value("${client.naver.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("X-Naver-Client-Id", clientId);
            template.header("X-Naver-Client-Secret", clientSecret);
        };
    }
}
