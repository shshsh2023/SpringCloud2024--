package com.song.cloud.controller;

import com.song.cloud.entities.PayDTO;
import com.song.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 20:26
 */
@RestController
public class OrderController {
//    private static final String PaymentSrv_URL = "http://localhost:8001";
    private static final String PaymentSrv_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping("/consumer/pay/delete/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/delete/" + id);
    }

    @PutMapping("/consumer/pay/update")
    public void updateOrder(@RequestBody PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData selectOrderById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
    }

    @GetMapping("/consumer/pay/getAll")
    public ResultData selectOrders(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }

    @GetMapping("/consumer/pay/getConfig")
    public ResultData getConfig(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/getConfig", ResultData.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/consumer/get/services")
    public ResultData getService(){
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        return ResultData.success(instances);
    }



}
