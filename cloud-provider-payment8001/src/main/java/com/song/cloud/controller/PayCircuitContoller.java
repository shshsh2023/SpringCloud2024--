package com.song.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/17 12:06
 */
@RestController
public class PayCircuitContoller {


    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("circuit id不能为负数");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello circuit, inputId: " + id + "\t" + IdUtil.simpleUUID();
    }

    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkHead(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("bulkhead id不能为负数");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello bulkhead, inputId: " + id + "\t" + IdUtil.simpleUUID();
    }

}
