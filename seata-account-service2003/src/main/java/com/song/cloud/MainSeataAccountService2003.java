package com.song.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 20:12
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.song.cloud.mapper")
public class MainSeataAccountService2003 {
    public static void main(String[] args) {
        SpringApplication.run(MainSeataAccountService2003.class, args);
    }
}