package com.song.cloud.controller;

import com.song.cloud.entities.Order;
import com.song.cloud.resp.ResultData;
import com.song.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 19:21
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public ResultData create(Order order){
        orderService.create(order);
        return ResultData.success(order);
    }

}
