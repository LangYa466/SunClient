package cn.langya.sun.modules.impl.move

import com.cubk.event.annotations.EventTarget
import cn.langya.sun.events.impl.MoveEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.values.StringValue


/**
 * @author LangYa
 * @ClassName Fly
 * @date 2023/12/31 10:43
 * @Version 1.0
 */

class Fly: Module("Fly",true,Category.Move) {

    private val modes = StringValue("Modes","Vanilla")

    init {
        modes.values.add("Jump")
        modes.values.add("Vanilla")
    }

    @EventTarget
    fun onMove(e: MoveEvent) {

        when(modes.get()) {

            "Vanilla" -> {
                if(mc.gameSettings.keyBindJump.isKeyDown) {
                    mc.player.motionY += 0.042
                }
            }

            "Jump" -> {
                mc.player.onGround = true
            }

        }

    }

}