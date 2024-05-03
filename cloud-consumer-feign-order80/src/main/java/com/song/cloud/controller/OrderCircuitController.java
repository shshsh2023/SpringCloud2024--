package com.song.cloud.controller;

import com.song.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/17 12:34
 */
@RestController
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name="cloud-payment-service", fallbackMethod = "myCircuitFallBack")
    public String myCircuitBreaker(@PathVariable("id") Integer id){
        return payFeignApi.myCircuit(id);
    }

    public String myCircuitFallBack(Integer id, Throwable t){
        return "系统繁忙，请稍后再试。原因：" + t.getMessage();
    }


    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkHeadFallBack", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkHead(@PathVariable("id") Integer id){
        return payFeignApi.myBulkHead(id);
    }

    public String myBulkHeadFallBack(Integer id, Throwable t){
        return "系统繁忙，请稍后再试。原因：" + t.getMessage();
    }


    @GetMapping("/feign/pay/bulkhead/poll/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkHeadPollFallBack", type = Bulkhead.Type.THREADPOOL)
    public CompletionStage<String> myBulkHeadPoll(@PathVariable("id") Integer id){

//        return payFeignApi.myBulkHead(id);
        return CompletableFuture.supplyAsync(()->payFeignApi.myBulkHead(id));
    }


    public CompletionStage<String> myBulkHeadPollFallBack(Integer id, Throwable t){
//        return "系统繁忙，请稍后再试。原因：" + t.getMessage();
        return CompletableFuture.supplyAsync(()-> "系统繁忙，请稍后再试。原因：" + t.getMessage());
    }


    @GetMapping("/feign/pay/ratelimiter/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimiterFallBack")
    public String myRatelimiter(@PathVariable("id") Integer id){
        return payFeignApi.myBulkHead(id);
    }

    public String myRatelimiterFallBack(Integer id, Throwable t){
        return "你被限流了，系统繁忙，请稍后再试。原因：" + t.getMessage();
    }


}
