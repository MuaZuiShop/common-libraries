package com.quy.common.servlet.config;

import com.quy.common.core.exception.ErrorCode;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:common-defaults.properties")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class OpenApiConfig {

    @Value("${gateway.url:http://localhost:8000}")
    private String gatewayUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
            .servers(List.of(new Server().url(gatewayUrl)))
            .info(new Info()
                .title("Hệ Thống API Documentation")
                .version("1.0.0")
                .description("Tài liệu tích hợp API cho các Microservices.\n\n"
                    + "### Bảng mã lỗi hệ thống\n"
                    + generateErrorCodeTable())
                .contact(new Contact()
                    .name("Backend Team")
                    .email("muazuishopp@gmail.com")))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Nhập JWT Token vào đây")));
    }

    private String generateErrorCodeTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("<details>\n");
        sb.append("<summary><b>Danh sách chi tiết mã lỗi hệ thống</b></summary>\n\n");
        sb.append("| HTTP | Mã lỗi | Lời nhắn |\n");
        sb.append("| :---: | :--- | :--- |\n");

        for (ErrorCode error : ErrorCode.values()) {
            int    code  = error.getHttpStatus().value();
            String color = code >= 500 ? "#ff4d4f" : code >= 400 ? "#faad14" : "#52c41a";
            sb.append(String.format(
                "| <font color='%s'>**%d**</font> | `%s` | %s |\n",
                color, code, error.getCode(), error.getMessage()
            ));
        }

        sb.append("\n</details>");
        return sb.toString();
    }
}