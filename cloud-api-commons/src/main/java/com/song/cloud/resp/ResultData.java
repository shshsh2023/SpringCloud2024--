package com.song.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 18:08
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;

    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> tResultData = new ResultData<>();
        tResultData.setCode(ReturnCodeEnum.RC200.getCode());
        tResultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        tResultData.setData(data);

        return tResultData;
    }

    public static <T> ResultData<T> fail(String code, String message){
        ResultData<T> tResultData = new ResultData<>();
        tResultData.setCode(code);
        tResultData.setMessage(message);
        tResultData.setData(null);

        return tResultData;
    }
}
