package com.song.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/27 16:13
 */
@RestController
@Slf4j
public class EmpowerController {


    @GetMapping("/empower")
    public String requestSentinel4(){
        log.info("测试sentinel授权规则empower");
        return "Sentinel授权规则";
    }

}
