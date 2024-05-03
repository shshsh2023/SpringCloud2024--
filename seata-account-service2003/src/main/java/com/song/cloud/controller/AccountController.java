package com.song.cloud.controller;

import com.song.cloud.resp.ResultData;
import com.song.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 20:17
 */

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;


    @RequestMapping("/account/decrease")
    public ResultData decrease(Long userId, Long money){
        accountService.decrease(userId, money);
        return ResultData.success("account 成功减少余额");
    }


}
