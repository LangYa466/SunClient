package cn.langya.sun.value


/**
 * @author LangYa
 * @ClassName FloatValue
 * @date 2023/12/15 17:23
 * @Version 1.0
 */

open class FloatValue(name: String,default: Float,max: Float,min: Float){

    var number: Float = 0f

    fun set(float: Float) {
        number = float
    }

    fun get(): Float {
        return number
    }

}