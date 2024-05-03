package com.song.cloud.apis;

import com.song.cloud.resp.ResultData;
import com.song.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/27 18:53
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，fallback服务降级 -.-");
    }
}
