package com.song.cloud.controller;

import com.song.cloud.apis.PayFeignSentinelApi;
import com.song.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/25 16:19
 */

@RestController
@RequestMapping("/nacos/consumer")
public class OrderNacosConsumer {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;


    @GetMapping("/pay/get/info/{id}")
    public ResultData consumerPaymentInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverUrl+"/nacos/pay/get/info/" + id, ResultData.class);
    }

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("/pay/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }

}
