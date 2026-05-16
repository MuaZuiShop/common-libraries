package com.quy.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements Code {
    OK(200, "Thao tác thành công"),
    CREATED(201, "Tạo mới tài nguyên thành công"),
    ACCEPTED(202, "Yêu cầu đã được tiếp nhận và đang trong quá trình xử lý"),
    NO_CONTENT(204, "Xử lý thành công nhưng không có dữ liệu trả về"),

    BAD_REQUEST(400, "Dữ liệu đầu vào không hợp lệ hoặc thiếu thông tin"),
    UNAUTHORIZED(401, "Không có quyền truy cập. Vui lòng cung cấp thông tin xác thực"),
    PAYMENT_REQUIRED(402, "Yêu cầu thanh toán để thực hiện thao tác này"),
    FORBIDDEN(403, "Truy cập bị từ chối. Bạn không có quyền thực hiện chức năng này"),
    NOT_FOUND(404, "Không tìm thấy tài nguyên yêu cầu"),
    METHOD_NOT_ALLOWED(405, "Phương thức HTTP không được hỗ trợ cho endpoint này"),
    NOT_ACCEPTABLE(406, "Định dạng dữ liệu yêu cầu không được chấp nhận"),
    REQUEST_TIMEOUT(408, "Hết thời gian chờ yêu cầu từ client"),
    CONFLICT(409, "Xung đột dữ liệu (Ví dụ: dữ liệu đã bị thay đổi bởi luồng khác)"),
    PAYLOAD_TOO_LARGE(413, "Kích thước dữ liệu gửi lên (payload) vượt quá giới hạn cho phép"),
    URI_TOO_LONG(414, "Độ dài của đường dẫn URI vượt quá giới hạn"),
    UNSUPPORTED_MEDIA_TYPE(415, "Định dạng Media Type (Content-Type) không được hệ thống hỗ trợ"),
    UNPROCESSABLE_ENTITY(422, "Dữ liệu đúng chuẩn JSON nhưng vi phạm logic nghiệp vụ đầu vào"),
    TOO_MANY_REQUESTS(429, "Vượt quá giới hạn số lượng request (Rate Limit). Vui lòng thử lại sau"),

    INTERNAL_SERVER_ERROR(500, "Lỗi hệ thống nội bộ"),
    NOT_IMPLEMENTED(501, "Tính năng này chưa được phát triển hoặc không được hỗ trợ"),
    BAD_GATEWAY(502, "Nhận được phản hồi không hợp lệ từ máy chủ đích (Bad Gateway)"),
    SERVICE_UNAVAILABLE(503, "Dịch vụ hiện không khả dụng (Có thể đang bảo trì hoặc quá tải)"),
    GATEWAY_TIMEOUT(504, "Hết thời gian chờ phản hồi từ máy chủ đích hoặc service khác (Gateway Timeout)"),

    DATA_ALREADY_EXISTS(1001, "Dữ liệu đã tồn tại trong hệ thống"),
    INVALID_STATUS(1002, "Trạng thái không hợp lệ để thực hiện thao tác này");

    private final int code;
    private final String message;
}