package cn.langya.sun.utils.misc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 如果有暂时不用的方法就加上这个注释 就不会看见烦人的 方法“XXX” 从未使用

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotWARING {}
