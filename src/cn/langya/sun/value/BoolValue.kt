package cn.langya.sun.value


/**
 * @author LangYa
 * @ClassName BoolValue
 * @date 2023/12/15 17:19
 * @Version 1.0
 */

class BoolValue(name: String,state: Boolean): ValueManager() {

    var state: Boolean = false

    fun set(boolean: Boolean) {
        state = boolean
    }

    fun get(): Boolean {
        return state
    }

}