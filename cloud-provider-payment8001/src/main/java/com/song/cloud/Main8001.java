package com.song.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;  //mapper4

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/12 16:44
 */

@SpringBootApplication
@MapperScan("com.song.cloud.mapper")
@EnableDiscoveryClient  //可以省略，默认开启
@RefreshScope
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}