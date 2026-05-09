package com.quy.common.util;

import com.quy.common.exception.BusinessException;
import com.quy.common.exception.CustomErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new BusinessException(CustomErrorCode.UNAUTHORIZED, "Không tìm thấy thông tin xác thực của người dùng");
        }

        // Vì trong Filter ta đã set principal là userId (String), nên có thể cast thẳng về String
        return authentication.getPrincipal().toString();
    }

    /**
     * Lấy danh sách các Roles/Quyền của user hiện tại.
     *
     * @return List<String> danh sách roles
     */
    public static List<String> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return List.of(); // Trả về list rỗng nếu chưa đăng nhập
        }

        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    /**
     * Kiểm tra xem request hiện tại đã được xác thực hay chưa.
     *
     * @return boolean
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal());
    }

    /**
     * Kiểm tra xem user hiện tại có sở hữu một role cụ thể nào đó hay không.
     *
     * @param role Tên role cần kiểm tra (ví dụ: "ROLE_ADMIN")
     * @return boolean
     */
    public static boolean hasRole(String role) {
        return getCurrentUserRoles().contains(role);
    }
}
