package com.song.cloud.controller;

import com.song.cloud.resp.ResultData;
import com.song.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 19:41
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public ResultData decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功");
    }
}
