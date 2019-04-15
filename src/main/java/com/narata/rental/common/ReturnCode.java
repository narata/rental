package com.narata.rental.common;

/**
 * @author narata
 * @since 2019/03/30
 */
public enum  ReturnCode {
    /**
     * OK 访问成功
     * BAD_REQUEST 请求失败
     * UNKNOWN 服务错误
     */
    OK(200),
    BAD_REQUEST(400),
    ERROR_INTERNAL(500);

    private int code;

    ReturnCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
