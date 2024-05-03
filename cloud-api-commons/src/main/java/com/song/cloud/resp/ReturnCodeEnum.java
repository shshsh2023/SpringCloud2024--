package com.song.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/13 17:09
 */

@Getter
public enum ReturnCodeEnum {
    Rc999("999", "操作XX失败"),
    RC200("200", "success"),
    RC201("201", "服务开启降级保护,请稍后再试!"),
    RC202("202", "热点参数限流,请稍后再试!"),
    RC203("203", "系统规则不满足要求,请稍后再试!"),
    RC204("204", "授权规则不通过,请稍后再试!"),
    RC403("403", "无访问权限,请联系管理员授予权限"),
    RC401("401", "匿名用户访问无权限资源时的异常"),
    RC404("404", "404页面找不到的异需"),
    RC500("500", "系统异常,请稍后重试"),
    RC375("375", "数学运算异带,请稍后重试"),

    INVALID_TOKEN("2001", "访问令牌不合法"),
    ACCESS_DENIED("2003", "没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED("1001", "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR("1002", "用户名或密码错误"),
    BUSINESS_ERROR("1004", "业务逻辑异常"),
    UNSUPPORTED_GRANT_TYPE("1003", "不支持的认证模式");

    private final String code;
    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ReturnCodeEnum getReturnCodeEnum01(String code){
        for(ReturnCodeEnum codeEnum: ReturnCodeEnum.values()){
            if(codeEnum.getCode().equalsIgnoreCase(code)){
                return codeEnum;
            }
        }
        return null;
    }

    public static ReturnCodeEnum getReturnCodeEnum(String code){
        return Arrays.stream(ReturnCodeEnum.values()).filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }


}
