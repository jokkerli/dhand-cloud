package com.jokeer.dhand.utils.testAspect;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 描述
     */
    String description() default "";
//
//    /**
//     * 方法类型 INSERT DELETE UPDATE OTHER
//     */
//    MethodType methodType() default MethodType.OTHER;
}

