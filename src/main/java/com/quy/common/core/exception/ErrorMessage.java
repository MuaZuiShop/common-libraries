package com.quy.common.core.exception;

public final class ErrorMessage {

    private ErrorMessage() {}

    // ==================== VI ====================
    public static final class VI {

        private VI() {}

        // Common
        public static final String UNAUTHORIZED           = "Bạn không có quyền truy cập";
        public static final String FORBIDDEN              = "Truy cập bị từ chối";
        public static final String NOT_FOUND              = "Không tìm thấy tài nguyên";
        public static final String INTERNAL_ERROR         = "Lỗi hệ thống, vui lòng thử lại sau";
        public static final String VALIDATION_ERROR       = "Dữ liệu không hợp lệ";
        public static final String BAD_REQUEST            = "Yêu cầu không hợp lệ";
        public static final String SERVICE_UNAVAILABLE    = "Dịch vụ tạm thời không khả dụng";
        public static final String GATEWAY_TIMEOUT        = "Dịch vụ phản hồi quá chậm, vui lòng thử lại";

        // Auth
        public static final String AUTH_INVALID_CREDENTIALS     = "Tên đăng nhập hoặc mật khẩu không đúng";
        public static final String AUTH_ACCOUNT_LOCKED          = "Tài khoản đã bị khóa";
        public static final String AUTH_ACCOUNT_DISABLED        = "Tài khoản chưa được kích hoạt";
        public static final String AUTH_TOKEN_INVALID           = "Token không hợp lệ";
        public static final String AUTH_TOKEN_EXPIRED           = "Token đã hết hạn";
        public static final String AUTH_TOKEN_MISSING           = "Thiếu token xác thực";
        public static final String AUTH_REFRESH_TOKEN_INVALID   = "Refresh token không hợp lệ";
        public static final String AUTH_REFRESH_TOKEN_EXPIRED   = "Refresh token đã hết hạn, vui lòng đăng nhập lại";
        public static final String AUTH_REFRESH_TOKEN_MISSING   = "Không tìm thấy refresh token";
        public static final String AUTH_USERNAME_EXISTED        = "Tên đăng nhập đã tồn tại";
        public static final String AUTH_EMAIL_EXISTED           = "Email đã được sử dụng";
        public static final String AUTH_PHONE_EXISTED           = "Số điện thoại đã được sử dụng";
        public static final String AUTH_REGISTER_FAILED         = "Đăng ký tài khoản thất bại";
        public static final String AUTH_LOGIN_FAILED            = "Đăng nhập thất bại";
        public static final String AUTH_LOGOUT_FAILED           = "Đăng xuất thất bại";
        public static final String AUTH_PASSWORD_WRONG          = "Mật khẩu hiện tại không đúng";
        public static final String AUTH_PASSWORD_NOT_MATCH      = "Mật khẩu xác nhận không khớp";
        public static final String AUTH_PASSWORD_TOO_WEAK       = "Mật khẩu quá yếu, cần ít nhất 8 ký tự";
        public static final String AUTH_OTP_INVALID             = "Mã OTP không hợp lệ";
        public static final String AUTH_OTP_EXPIRED             = "Mã OTP đã hết hạn";
        public static final String AUTH_OTP_SEND_FAILED         = "Gửi OTP thất bại, vui lòng thử lại";
        public static final String AUTH_REQUIRES_REFRESH        = "Access token hết hạn, hãy dùng refresh token để lấy token mới";
        public static final String AUTH_SESSION_EXPIRED         = "Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại";
        public static final String AUTH_ROLE_NOT_FOUND          = "Vai trò không tồn tại";
        public static final String AUTH_PERMISSION_DENIED       = "Bạn không có quyền thực hiện thao tác này";

        // Customer
        public static final String CUSTOMER_NOT_FOUND          = "Không tìm thấy khách hàng";
        public static final String CUSTOMER_ALREADY_EXISTS      = "Khách hàng đã tồn tại";
        public static final String CUSTOMER_EMAIL_EXISTED       = "Email khách hàng đã được sử dụng";
        public static final String CUSTOMER_PHONE_EXISTED       = "Số điện thoại khách hàng đã được sử dụng";
        public static final String CUSTOMER_CREATE_FAILED       = "Tạo khách hàng thất bại";
        public static final String CUSTOMER_UPDATE_FAILED       = "Cập nhật thông tin khách hàng thất bại";
        public static final String CUSTOMER_DELETE_FAILED       = "Xóa khách hàng thất bại";
        public static final String CUSTOMER_INACTIVE            = "Tài khoản khách hàng đã bị vô hiệu hóa";
        public static final String CUSTOMER_ADDRESS_NOT_FOUND   = "Không tìm thấy địa chỉ";
        public static final String CUSTOMER_ADDRESS_LIMIT       = "Đã đạt giới hạn số lượng địa chỉ";
    }

    // ==================== EN ====================
    public static final class EN {

        private EN() {}

        // Common
        public static final String UNAUTHORIZED           = "You are not authorized to access this resource";
        public static final String FORBIDDEN              = "Access denied";
        public static final String NOT_FOUND              = "Resource not found";
        public static final String INTERNAL_ERROR         = "Internal server error, please try again later";
        public static final String VALIDATION_ERROR       = "Validation failed";
        public static final String BAD_REQUEST            = "Bad request";
        public static final String SERVICE_UNAVAILABLE    = "Service temporarily unavailable";
        public static final String GATEWAY_TIMEOUT        = "Service response timeout, please try again";

        // Auth
        public static final String AUTH_INVALID_CREDENTIALS     = "Invalid username or password";
        public static final String AUTH_ACCOUNT_LOCKED          = "Account is locked";
        public static final String AUTH_ACCOUNT_DISABLED        = "Account is not activated";
        public static final String AUTH_TOKEN_INVALID           = "Invalid token";
        public static final String AUTH_TOKEN_EXPIRED           = "Token has expired";
        public static final String AUTH_TOKEN_MISSING           = "Missing authentication token";
        public static final String AUTH_REFRESH_TOKEN_INVALID   = "Invalid refresh token";
        public static final String AUTH_REFRESH_TOKEN_EXPIRED   = "Refresh token has expired, please login again";
        public static final String AUTH_REFRESH_TOKEN_MISSING   = "Refresh token not found";
        public static final String AUTH_USERNAME_EXISTED        = "Username already exists";
        public static final String AUTH_EMAIL_EXISTED           = "Email is already in use";
        public static final String AUTH_PHONE_EXISTED           = "Phone number is already in use";
        public static final String AUTH_REGISTER_FAILED         = "Registration failed";
        public static final String AUTH_LOGIN_FAILED            = "Login failed";
        public static final String AUTH_LOGOUT_FAILED           = "Logout failed";
        public static final String AUTH_PASSWORD_WRONG          = "Current password is incorrect";
        public static final String AUTH_PASSWORD_NOT_MATCH      = "Password confirmation does not match";
        public static final String AUTH_PASSWORD_TOO_WEAK       = "Password is too weak, minimum 8 characters required";
        public static final String AUTH_OTP_INVALID             = "Invalid OTP code";
        public static final String AUTH_OTP_EXPIRED             = "OTP code has expired";
        public static final String AUTH_OTP_SEND_FAILED         = "Failed to send OTP, please try again";
        public static final String AUTH_REQUIRES_REFRESH        = "Access token expired, please use refresh token to get a new one";
        public static final String AUTH_SESSION_EXPIRED         = "Session has expired, please login again";
        public static final String AUTH_ROLE_NOT_FOUND          = "Role not found";
        public static final String AUTH_PERMISSION_DENIED       = "You do not have permission to perform this action";

        // Customer
        public static final String CUSTOMER_NOT_FOUND          = "Customer not found";
        public static final String CUSTOMER_ALREADY_EXISTS      = "Customer already exists";
        public static final String CUSTOMER_EMAIL_EXISTED       = "Customer email is already in use";
        public static final String CUSTOMER_PHONE_EXISTED       = "Customer phone number is already in use";
        public static final String CUSTOMER_CREATE_FAILED       = "Failed to create customer";
        public static final String CUSTOMER_UPDATE_FAILED       = "Failed to update customer";
        public static final String CUSTOMER_DELETE_FAILED       = "Failed to delete customer";
        public static final String CUSTOMER_INACTIVE            = "Customer account is disabled";
        public static final String CUSTOMER_ADDRESS_NOT_FOUND   = "Address not found";
        public static final String CUSTOMER_ADDRESS_LIMIT       = "Address limit reached";
    }
}