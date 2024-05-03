package com.song.cloud.apis;

import com.song.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 18:50
 */

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {

    /**
     * 根据用户id 对 用户进行修改
     * @param userId
     * @param money
     */
    @RequestMapping("/account/decrease")
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);


}
