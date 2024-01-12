package cn.langya.sun.utils.player

import cn.langya.sun.utils.Utils


/**
 * @author LangYa
 * @ClassName MoveUtils
 * @date 2023/12/31 10:20
 * @Version 1.0
 */

object MoveUtils: Utils() {
    fun isMoving(): Boolean {
        return mc.player != null && (mc.player.movementInput.moveStrafe !== 0.0f || mc.player.movementInput.moveStrafe !== 0.0f)
    }
}