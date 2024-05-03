package com.song.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/17 20:28
 */
@RestController
public class PayMicrometerController {
    
    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id){
        return "Hello， 欢迎来到 myMicrometer inputId： " + id  + "\t 服务返回" + IdUtil.simpleUUID();
    }

}
