package com.song.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 16:55
 */
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {

    /**
     * 根据产品id对商品的数量进行修改
     * @param productId
     * @param count
     */
    @RequestMapping("/storage/decrease")
    void decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
