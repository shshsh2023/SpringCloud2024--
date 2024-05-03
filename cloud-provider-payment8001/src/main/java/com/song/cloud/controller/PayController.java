package com.song.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.song.cloud.entities.Pay;
import com.song.cloud.entities.PayDTO;
import com.song.cloud.resp.ResultData;
import com.song.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/12 19:06
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值 ： " + i);
    }

    @DeleteMapping("/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水，流水id做参数")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int delete = payService.delete(id);
        return ResultData.success(delete);
    }

    @PutMapping("/pay/update")
    @Operation(summary = "更新", description = "更新支付流水方法，json串做参数")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDTO, pay);
        int update = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + update);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查询单条", description = "查询一条支付流水，id作为参数")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) throws InterruptedException {
        Pay byId = payService.getById(id);
//        TimeUnit.SECONDS.sleep(4);
        return ResultData.success(byId);
    }

    @GetMapping("/pay/getAll")
    @Operation(summary = "查询所有", description = "查询所有流水")
    public ResultData<List<Pay>> getAll() {
        List<Pay> all = payService.getAll();
        return ResultData.success(all);
    }


    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/getConfig")
    public ResultData<Map<String, String>> getConfig(@Value("${song.info}") String info) throws InterruptedException {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("port", port);
        stringStringHashMap.put("info", info);
        return ResultData.success(stringStringHashMap);
    }

}
