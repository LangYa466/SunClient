package cn.langya.sun.events


/**
 * @author LangYa
 * @ClassName MoveEvent
 * @date 2023/12/14 20:58
 * @Version 1.0
 */

class MoveEvent(var x: Double,var y: Double,var z: Double): CancellableEvent() {

    fun zero() {
        this.x = 0.0
        y = 0.0
        z = 0.0
    }

    fun zeroXZ() {
        x = 0.0
        z = 0.0
    }

}