package com.song.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/25 21:06
 */

@Service
public class FlowLimitService {

    @SentinelResource(value = "common")
    public void common() {
        System.out.println("-----Flow limit service come in");
    }


}
