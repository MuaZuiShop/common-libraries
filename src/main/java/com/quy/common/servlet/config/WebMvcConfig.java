package com.quy.common.servlet.config;

import com.quy.common.servlet.services.UserContextInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Chỉ dùng file này khi service con tự define SecurityFilterChain riêng
 * (tức là CommonSecurityAutoConfiguration bị bỏ qua).
 * Nếu dùng CommonSecurityAutoConfiguration thì UserContextInterceptor
 * đã được đăng ký sẵn, không cần import WebMvcConfig nữa.
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserContextInterceptor userContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userContextInterceptor).addPathPatterns("/**");
    }
}