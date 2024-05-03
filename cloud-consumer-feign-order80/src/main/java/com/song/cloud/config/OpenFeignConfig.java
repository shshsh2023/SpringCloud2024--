package com.song.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/16 22:50
 */
@Configuration
public class OpenFeignConfig {

    @Bean
    public Retryer retryer(){
        // 初始间隔时间100ms  重试最大间隔时间1s  最大尝试次数3
        return Retryer.NEVER_RETRY;
//        return new Retryer.Default(100, 1, 3);
    }

    //定义日志bean
//    @Bean
//    Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
}
