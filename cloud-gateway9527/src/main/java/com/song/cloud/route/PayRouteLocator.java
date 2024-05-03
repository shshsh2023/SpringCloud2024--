package com.song.cloud.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/23 15:41
 */
//@Component
//public class PayRouteLocator {
//
//    @Bean
//    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("pay_routh1", r -> r.path("/pay/gateway/get/**").and().cookie("username", "xxyy")
//                        .uri("lb://cloud-payment-service"))
//                .route("pay_routh2", r -> r.path("/pay/gateway/info/**")
//                        .uri("lb://cloud-payment-service"))
//                .build();
//    }
//
//}
