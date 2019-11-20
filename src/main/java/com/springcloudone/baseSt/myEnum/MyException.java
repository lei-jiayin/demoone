package com.springcloudone.baseSt.myEnum;

import com.springcloudone.baseSt.myEnum.baseInterface.ErrorCode;

/**
 * 自定义异常处理
 * @author xw
 * @date 2019/11/13 10:15
 */
public class MyException extends RuntimeException {

    /**
     * 错误码
     */
    protected final ErrorCode errorCode;

    /**
     * 错误码构造
     * @param errorCode
     */
    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(String detailMessage){
        super(detailMessage);
        this.errorCode = ExceptionEnum.UNSPECIFIED;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
