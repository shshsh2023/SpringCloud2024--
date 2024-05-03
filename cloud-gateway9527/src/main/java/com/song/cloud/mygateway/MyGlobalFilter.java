package com.song.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/24 10:00
 */
@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";  //开始调用方法的时间

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1先记录下访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                Long endVisitTime = System.currentTimeMillis();
                Long visitTime = endVisitTime - beginVisitTime;
                log.info("接口访问耗时：{} ms", visitTime);
                log.info("访问接口主机：{}", exchange.getRequest().getURI().getHost());
                log.info("访问接口端口：{}", exchange.getRequest().getURI().getPort());
                log.info("访问接口URL： {}", exchange.getRequest().getURI().getPath());
                log.info("访问接口url后面的参数：{}", exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口url后面的参数：{}", exchange.getRequest().getURI());
                log.info("===============分割线==================");
                System.out.println();
            }
        }));
    }


    /**
     * 数字越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
