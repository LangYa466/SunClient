package cn.langya.sun.modules.impl.combat

import cn.enaium.cf4m.annotation.Event
import cn.langya.sun.events.impl.AttackEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.values.StringValue


/**
 * @author LangYa
 * @ClassName Criticals
 * @date 2023/12/31 10:34
 * @Version 1.0
 */

class Criticals: Module("Criticals",true,Category.Combat) {

    private val modes = StringValue("Modes","Jump")

    init {
        modes.values.add("Jump")
        modes.values.add("Fake")
    }

    @Event
    fun onAttack(event: AttackEvent) {
        when(modes.get()) {

            "Jump" -> {
                if(mc.player.onGround) {
                    mc.player.jump()
                }
            }

            "Fake" -> {
                mc.player.onCriticalHit(event.target)
            }

        }
    }

}