package com.song.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.AfterRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/20 21:50
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }


    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            //检查request的参数里面，userType是否为指定的值，符合配置就通过
            Optional<String> userType = Optional.ofNullable(serverWebExchange.getRequest().getQueryParams().getFirst("userType"));
            //如果说参数存在，就和config的数据进行比较
            return userType.map(o -> o.equalsIgnoreCase(config.getUserType())).orElse(false);
        };
    }

    @Setter
    @Getter
    @Validated
    public static class Config {
        @NotEmpty
        private String userType;
    }
}
