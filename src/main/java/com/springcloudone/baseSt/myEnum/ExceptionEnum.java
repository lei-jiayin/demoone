package com.springcloudone.baseSt.myEnum;

import com.springcloudone.baseSt.myEnum.baseInterface.ErrorCode;

/**
 * @author xw
 * @date 2019/11/13 9:48
 */
public enum  ExceptionEnum implements ErrorCode {
    /**
     * 500 服务器报错
     */
    UNSPECIFIED(500, "内部服务器错误！"),
    NO_SERVICE(404, "服务资源丢失！");


    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误码描述
     */
    private final String description;

    ExceptionEnum(final Integer code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }}
