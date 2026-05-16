package com.quy.common.config;

import com.quy.common.services.exception.CustomErrorCode;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:common-defaults.properties")
public class OpenApiConfig {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .servers(List.of(new Server().url(gatewayUrl)))

                .info(new Info()
                        .title("Hệ Thống API Documentation")
                        .version("1.0.0")
                        .description("Tài liệu tích hợp API cho các Microservices. \n\n" +
                                "### Bảng mã lỗi hệ thống (Error Codes)\n" +
                                generateErrorCodeTable())
                        .contact(new Contact().name("Backend Team").email("muazuishopp@gmail.com")))

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
        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append("<details>\n");
        tableBuilder.append("<summary><b>Danh sách chi tiết mã lỗi hệ thống</b></summary>\n\n");

        tableBuilder.append("| Mã lỗi (Code) | Lời nhắn (Message) |\n");
        tableBuilder.append("| :--- | :--- |\n");

        for (CustomErrorCode error : CustomErrorCode.values()) {
            String color;
            int code = error.getCode();

            if (code >= 500) {
                color = "#ff4d4f";
            } else if (code >= 400) {
                color = "#faad14";
            } else {
                color = "#52c41a";
            }

            tableBuilder.append(String.format(
                    "| <font color='%s'>**%d**</font> | <font color='%s'>%s</font> |\n",
                    color, code, color, error.getMessage()
            ));
        }

        tableBuilder.append("\n</details>");
        return tableBuilder.toString();
    }
}