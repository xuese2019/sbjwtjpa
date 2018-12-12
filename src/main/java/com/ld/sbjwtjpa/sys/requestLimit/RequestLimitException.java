package com.ld.sbjwtjpa.sys.requestLimit;

/**
 * 超出访问次数限制异常
 */
public class RequestLimitException extends RuntimeException {

    public RequestLimitException(String msg) {
        super(msg);
    }

    public RequestLimitException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
