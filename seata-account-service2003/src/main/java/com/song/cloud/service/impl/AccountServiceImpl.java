package com.song.cloud.service.impl;

import com.song.cloud.mapper.AccountMapper;
import com.song.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 20:18
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("--------------------> account service 开始扣除余额");
        accountMapper.decrease(userId, money);
//        myTimeOut();
        log.info("--------------------> account service 结束扣除余额");
    }

    public static void myTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
