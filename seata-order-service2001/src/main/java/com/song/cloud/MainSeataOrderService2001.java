package com.song.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 16:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.song.cloud.mapper")
@EnableFeignClients
public class MainSeataOrderService2001 {
    public static void main(String[] args) {
        SpringApplication.run(MainSeataOrderService2001.class, args);
    }
}