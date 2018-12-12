package com.ld.sbjwtjpa.sys.preventDuplication;

import java.lang.annotation.*;

/**
 * 防止表单重复提交注解
 * session 版本
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {

    //保存重复提交标记 默认为需要保存
    boolean save() default true;
}
