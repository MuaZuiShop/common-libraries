package com.quy.common.servlet.services;

import com.quy.common.core.security.ERole;
import java.util.List;

public final class UserContext {

    private UserContext() {}

    private static final ThreadLocal<String>      currentUserId    = new ThreadLocal<>();
    private static final ThreadLocal<List<ERole>> currentUserRoles = new ThreadLocal<>();

    public static void setUserId(String userId) {
        currentUserId.set(userId);
    }

    public static String getUserId() {
        return currentUserId.get();
    }

    public static void setUserRoles(List<ERole> roles) {
        currentUserRoles.set(roles);
    }

    public static List<ERole> getUserRoles() {
        return currentUserRoles.get();
    }

    public static boolean hasRole(ERole role) {
        List<ERole> roles = currentUserRoles.get();
        return roles != null && roles.contains(role);
    }

    public static boolean isAuthenticated() {
        return currentUserId.get() != null;
    }

    public static void clear() {
        currentUserId.remove();
        currentUserRoles.remove();
    }
}