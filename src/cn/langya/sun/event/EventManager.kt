package cn.langya.sun.event

import cn.langya.sun.modules.impl.client.Client
import cn.langya.sun.modules.impl.combat.KillAura
import cn.langya.sun.utils.ClientUtils


/**
 * @author LangYa
 * @ClassName EventManager
 * @date 2023/12/17 14:40
 * @Version 1.0
 */

class EventManager {

    fun onUpdate() {
        Client().onUpdate()
        KillAura().onUpdate()
        ClientUtils.loginfo("onUpdate Event")
    }

    fun onMove() {

    }

    fun onRender2D() {
        ClientUtils.loginfo("onRender2D Event")
    }

    fun onRender3D() {
        ClientUtils.loginfo("onRender3D Event")
    }

    fun onAttack() {
        ClientUtils.loginfo("onAttack Event")
    }

}