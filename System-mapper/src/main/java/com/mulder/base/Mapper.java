package com.mulder.base;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mulder on 16/5/18.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapper {
    String name();
}

