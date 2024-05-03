package com.song.cloud.apis;

import cn.hutool.core.util.IdUtil;
import com.song.cloud.entities.PayDTO;
import com.song.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/16 16:48
 */
//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/delete/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id);

    @PutMapping("/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    @GetMapping("/pay/getAll")
    public ResultData getAll();

    @GetMapping("/pay/getConfig")
    public ResultData getConfig();

    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    @GetMapping("/pay/bulkhead/{id}")
    String myBulkHead(@PathVariable("id") Integer id);

    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    // PayGatewayController 接口
    @GetMapping("/pay/gateway/get/{id}")
    public ResultData getByIdWithGateway(@PathVariable("id") Integer id, @RequestParam Map<String, Object> parms);

    @GetMapping("/pay/gateway/info")
    public ResultData getGatewayInfo();
}
