package com.song.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Block;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/27 15:27
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/rate/limit/a")
    @SentinelResource(value = "testRateLimit", blockHandler = "myBlockHandle")
    public String testRateLimit() {
        return "测试非默认限流提示";
    }

    public String myBlockHandle(BlockException blockException) {
        return "myBlockHandle返回限流提示" + blockException.getMessage();
    }

    @GetMapping("/rate/limit/doAction/{id}")
    @SentinelResource(value = "doAction", blockHandler = "doActionBlockHandle", fallback = "doActingFallback")
    public String doAction(@PathVariable("id") Integer id) {
        if (id == 0) {
            throw new RuntimeException("id等于0直接异常");
        }
        return "doAction : " + id;
    }

    public String doActionBlockHandle(BlockException blockException) {
        log.error("sentinel配置自定义限流了：{}", blockException);
        return "sentinel配置自定义限流了";
    }

    public String doActingFallback(@PathVariable("id") Integer id, Throwable e) {
        log.error("程序逻辑异常了:{}", e);
        return "程序逻辑异常了" + "\t" + e.getMessage();
    }


    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "bh_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey";
    }

    public String bh_testHotKey(BlockException blockException) {
        return "bh_testHotKey" + blockException.getMessage();
    }


}
