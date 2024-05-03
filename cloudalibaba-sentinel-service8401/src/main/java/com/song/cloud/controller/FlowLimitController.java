package com.song.cloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.song.cloud.service.FlowLimitService;
import io.micrometer.core.instrument.util.TimeUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/25 19:33
 */
@RestController
@RequestMapping("/sentinel")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "--------------------------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "---------------------------testB";
    }

    @Resource
    private FlowLimitService flowLimitService;


    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "--------------------------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "---------------------------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--测试：新增熔断规则-慢调用比例");
        return "---------------------------testE";
    }


    @GetMapping("/testF")
    public String testF() {

        int a = 10 / 0;
        System.out.println("--测试：新增熔断规则--异常比例");
        return "---------------------------testF";
    }
}
