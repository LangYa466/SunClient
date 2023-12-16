package cn.langya.sun

import cn.langya.sun.modules.ModuleManager
import cn.langya.sun.ui.UIManager
import cn.langya.sun.ui.impl.notification.NotificationManager
import cn.langya.sun.utils.ClientUtils
import com.google.common.eventbus.EventBus

object Sun {

    const val name = "SunClinet"
    const val version = "1.0"
    const val author = "LangYa"

    var event: EventBus? = null

    //Manager
    var moduleManager: ModuleManager? = null
    var uiManager: UIManager? = null

    fun initClient() {
        ClientUtils.loginfo("SunClient initing")
        event = EventBus()
        moduleManager = ModuleManager()
        moduleManager!!.init()
        uiManager = UIManager(NotificationManager.x,NotificationManager.y,NotificationManager.width,NotificationManager.height)
        ClientUtils.loginfo("SunClient okay")
    }

}