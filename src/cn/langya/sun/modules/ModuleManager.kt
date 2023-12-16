package cn.langya.sun.modules

import cn.langya.sun.modules.impl.client.Client
import cn.langya.sun.modules.impl.combat.KillAura
import cn.langya.sun.utils.ClientUtils

/**
 * @author LangYa
 * @ClassName Module
 * @date 2023/12/14 21:04
 * @Version 1.0
 */

class ModuleManager {

    var modules = mutableListOf<Module>()

    private fun registerModule(module: Module) {
        modules.add(module)
    }

    fun registermodules() {
        registerModule(Client())
        registerModule(KillAura())
    }

    fun init() {
        ClientUtils.loginfo("[模块系统] 加载模块中...")
        registermodules()
        ClientUtils.loginfo("[模块系统] 已加载模块: ${modules.size}")
    }

}
