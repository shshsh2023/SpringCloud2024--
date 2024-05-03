package com.song.cloud.controller;

import com.song.cloud.apis.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/17 20:38
 */
@RestController
public class OrderMicrometerController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id){
        return payFeignApi.myMicrometer(id);
    }



}
