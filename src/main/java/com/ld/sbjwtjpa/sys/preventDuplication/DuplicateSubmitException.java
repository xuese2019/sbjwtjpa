package com.ld.sbjwtjpa.sys.preventDuplication;

/**
 * 重复提交异常类
 */
public class DuplicateSubmitException extends RuntimeException {
    public DuplicateSubmitException(String msg) {
        super(msg);
    }

    public DuplicateSubmitException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
