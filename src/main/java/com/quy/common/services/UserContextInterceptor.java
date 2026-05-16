package com.quy.common.services;

import com.quy.common.security.ERole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("X-User-Id");
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
                System.err.println("Lỗi parse role từ header: " + rolesHeader);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }
}