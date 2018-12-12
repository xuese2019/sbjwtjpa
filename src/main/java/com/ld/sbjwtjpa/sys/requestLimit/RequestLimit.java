package com.ld.sbjwtjpa.sys.requestLimit;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     *
     * 允许访问的次数，默认值10次
     */
    int count() default 10;

    /**
     *
     * 时间段，单位为毫秒，默认值5000ms
     */
    long time() default 5000;
}
