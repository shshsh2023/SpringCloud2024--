package com.song.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.song.cloud.entities.Pay;
import com.song.cloud.resp.ResultData;
import com.song.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/18 19:33
 */
@RestController
public class PayGateWayController {

    @Resource
    private PayService payService;

    @GetMapping("/pay/gateway/get/{id}")
    @Operation(summary = "查询单条", description = "查询一条支付流水，id作为参数")
    public ResultData<Pay> getById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getQueryString());

        Pay byId = payService.getById(id);
        return ResultData.success(byId);
    }

    @GetMapping("/pay/gateway/info")
    public ResultData<String> getGatewayInfo(){
        return ResultData.success("gateway info test " + IdUtil.simpleUUID());
    }


}
