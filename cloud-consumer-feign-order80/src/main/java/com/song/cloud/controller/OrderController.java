package com.song.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.song.cloud.apis.PayFeignApi;
import com.song.cloud.entities.PayDTO;
import com.song.cloud.resp.ResultData;
import io.micrometer.core.instrument.util.TimeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 20:26
 */
@RestController
@RequestMapping("/feign")
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/consumer/pay/add")
    @Operation(summary = "新增", description = "新增支付流水")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @DeleteMapping("/consumer/pay/delete/{id}")
    @Operation(summary = "删除")
    public void deleteOrder(@PathVariable("id") Integer id) {
        payFeignApi.deletePay(id);
    }

    @PutMapping("/consumer/pay/update")
    @Operation(summary = "更新")
    public void updateOrder(@RequestBody PayDTO payDTO) {
        payFeignApi.updatePay(payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    @Operation(summary = "查询单条")
    public ResultData selectOrderById(@PathVariable("id") Integer id) {
        ResultData resultData = null;
        try{
            System.out.println("调用服务开始------"  + DateUtil.now());
            resultData = payFeignApi.getById(id);
            System.out.println("调用服务结束------"  + DateUtil.now());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用服务超时------"  + DateUtil.now());
        }
        return resultData;
    }

    @GetMapping("/consumer/pay/getAll")
    @Operation(summary = "查询所有")
    public ResultData selectOrders() {
        return payFeignApi.getAll();
    }

    @GetMapping("/consumer/pay/get/services")
    public ResultData getConfig() {
        return payFeignApi.getConfig();
    }

}
