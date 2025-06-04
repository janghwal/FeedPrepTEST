package com.example.feedprep.common.security.annotation;
import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthUser {
    boolean required() default true;
}
