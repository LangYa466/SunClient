package cn.langya.sun.modules.impl.move

import cn.enaium.cf4m.annotation.Event
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

class Fly: Module("飞行",true,Category.Move) {

    private val modes = StringValue("Modes","Vanilla")

    init {
        modes.values.add("Jump")
        modes.values.add("Vanilla")
    }

    @Event
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