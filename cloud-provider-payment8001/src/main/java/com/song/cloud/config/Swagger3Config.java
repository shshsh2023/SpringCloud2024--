package com.song.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 16:37
 */
@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }


    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder().group("其他微服务模块").pathsToMatch("/other/**", "/others").build();
    }

    public OpenAPI docsOpenApi() {
        return new OpenAPI().info(
                        new Info().title("cloud2024")
                                .description("通用设计rest")
                                .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.song.com")
                        .url("https://yiyan.baidu.com"));

    }
}
