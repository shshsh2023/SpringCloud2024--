package com.song.cloud.exp;

import com.song.cloud.resp.ResultData;
import com.song.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 18:24
 */
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){

        System.out.println("#######come in global exception handler");
        log.error("全局异常信息: {}", e.getMessage(), e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());


    }

}
