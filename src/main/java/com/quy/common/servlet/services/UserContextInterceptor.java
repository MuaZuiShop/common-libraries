package com.quy.common.servlet.services;

import com.quy.common.core.security.ERole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId      = request.getHeader("X-User-Id");
        String rolesHeader = request.getHeader("X-User-Roles");

        if (userId != null) {
            UserContext.setUserId(userId);
        }

        if (rolesHeader != null && !rolesHeader.isEmpty()) {
            try {
                List<ERole> roles = Arrays.stream(rolesHeader.split(","))
                    .map(String::trim)
                    .map(ERole::valueOf)
                    .collect(Collectors.toList());
                UserContext.setUserRoles(roles);
            } catch (IllegalArgumentException e) {
                log.warn("Không thể parse role từ header: {}", rolesHeader);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        Exception ex
    ) {
        UserContext.clear();
    }
}