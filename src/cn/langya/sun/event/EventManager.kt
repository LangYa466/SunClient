package cn.langya.sun.event

import cn.langya.sun.modules.impl.client.Client


/**
 * @author LangYa
 * @ClassName EventManager
 * @date 2023/12/17 14:40
 * @Version 1.0
 */

class EventManager {

    fun onUpdate() {
        Client().onUpdate()
    }

    fun onMove() {

    }

    fun onRender2D() {

    }

    fun onRender3D() {

    }

    fun onAttack() {

    }

}