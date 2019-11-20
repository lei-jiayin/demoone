package com.springcloudone.baseSt.myEnum.baseInterface;

/**
 * 错误码接口
 */
public interface ErrorCode {
    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取错误描述
     * @return
     */
    String getDescription();
}
