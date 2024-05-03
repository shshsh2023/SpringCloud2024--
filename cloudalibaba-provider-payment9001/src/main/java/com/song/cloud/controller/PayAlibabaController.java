package com.song.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.song.cloud.entities.PayDTO;
import com.song.cloud.resp.ResultData;
import com.song.cloud.resp.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/25 14:33
 */
@RestController
@RequestMapping("/nacos")
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/get/info/{id}")
    public ResultData<String> getPayInfo(@PathVariable("id") Integer id) {
        return ResultData.success("nacos registry, server port: " + serverPort + " \t id: " + id);
    }

    //openfeign + Sentinel 整合，进行服务降级和流量监控
    @GetMapping("/pay/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handleBlockHandler")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        PayDTO payDTO = new PayDTO();

        payDTO.setId(1024);
        payDTO.setOrderNo(orderNo);
        payDTO.setAmount(BigDecimal.ONE);
        payDTO.setPayNo("pay:" + IdUtil.fastUUID());
        payDTO.setUserId(1);

        return ResultData.success(payDTO);
    }

    public ResultData handleBlockHandler(@PathVariable("orderNo") String orderNo, BlockException blockException) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),
                "getPayByOrderNo服务不可用, " + "触发Sentinel流控配置规则" + "\t" + "-.-");
    }
}
