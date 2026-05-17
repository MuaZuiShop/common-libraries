package com.quy.common.servlet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quy.common.core.exception.ApiResponse;
import com.quy.common.servlet.services.GatewayHeaderAuthenticationFilter;
import com.quy.common.servlet.services.UserContextInterceptor;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CommonSecurityAutoConfiguration implements WebMvcConfigurer {

    private final GatewayHeaderAuthenticationFilter gatewayHeaderAuthenticationFilter;
    private final UserContextInterceptor userContextInterceptor;
    private final ObjectMapper objectMapper;

    /**
     * Bean này chỉ tạo khi service con CHƯA tự define SecurityFilterChain.
     * Nếu service con cần config riêng (permitAll path khác, hasAuthority...),
     * hãy tự tạo SecurityFilterChain bean — bean này sẽ bị bỏ qua.
     */
    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(gatewayHeaderAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/actuator/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, e) ->
                    writeJsonError(response, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Vui lòng đăng nhập")
                )
                .accessDeniedHandler((request, response, e) ->
                    writeJsonError(response, HttpStatus.FORBIDDEN, "FORBIDDEN", "Không có quyền truy cập")
                )
            );

        return http.build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userContextInterceptor).addPathPatterns("/**");
    }

    private void writeJsonError(HttpServletResponse response,
                                HttpStatus status,
                                String code,
                                String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            objectMapper.writeValueAsString(ApiResponse.error(code, message))
        );
    }
}