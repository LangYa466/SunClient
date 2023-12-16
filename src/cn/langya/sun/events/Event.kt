package cn.langya.sun.events


/**
 * @author LangYa
 * @ClassName Event
 * @date 2023/12/16 13:48
 * @Version 1.0
 */

//我写这个注释是因为他一直显示没有使用方法所以为了不让他烦我就写了个注释
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Event