package cn.com.ktm.mt.model.constant;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 陈臻 on 2017/4/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
public @interface ResponseCode {
    public String value();
}
