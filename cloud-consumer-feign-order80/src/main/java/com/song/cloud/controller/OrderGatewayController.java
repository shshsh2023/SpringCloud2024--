package com.song.cloud.controller;

import com.song.cloud.apis.PayFeignApi;
import com.song.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.Header;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/18 19:56
 */
@RestController
@RequestMapping("/feign")
public class OrderGatewayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/gateway/get/{id}")
    public ResultData getByIdWithGateway(@PathVariable("id") Integer id, @RequestParam Map<String, Object> parms){
        System.out.println(parms);
        return payFeignApi.getByIdWithGateway(id, parms);
    }

    @GetMapping("/pay/gateway/info")
    public ResultData getGatewayInfo(){
        return payFeignApi.getGatewayInfo();
    }

    public static void main(String[] args){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }

}
