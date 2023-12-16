package cn.langya.sun.value

import java.util.ArrayList


/**
 * @author LangYa
 * @ClassName ValueManager
 * @date 2023/12/15 21:43
 * @Version 1.0
 */

open class ValueManager {
    val values: MutableList<Value> = ArrayList()

    open fun add(value: Value) {
        values.add(value)
    }
}

open class Value()