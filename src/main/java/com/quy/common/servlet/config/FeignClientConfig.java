package com.quy.common.servlet.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest; // Dùng javax.servlet.http.HttpServletRequest nếu dùng Spring Boot 2.x

import java.util.Arrays;
import java.util.List;

/**
 * Tự động truyền các header được chỉ định từ request hiện tại sang
 * các Feign client call (service-to-service).
 */
@Configuration
@ConditionalOnClass(RequestInterceptor.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class FeignClientConfig {

    private static final List<String> FORWARDED_HEADERS = Arrays.asList(
        "X-User-Id",
        "X-User-Roles"
    );

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                for (String headerName : FORWARDED_HEADERS) {
                    String headerValue = request.getHeader(headerName);

                    if (headerValue != null && !headerValue.trim().isEmpty()) {
                        template.header(headerName, headerValue);
                    }
                }
            }
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}