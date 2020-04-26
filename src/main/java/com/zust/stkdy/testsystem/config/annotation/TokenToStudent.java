package com.zust.stkdy.testsystem.config.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToStudent {
    String value() default "student";
}
