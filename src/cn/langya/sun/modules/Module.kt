package cn.langya.sun.modules

import cn.langya.sun.Sun
import cn.langya.sun.ui.impl.notification.NotificationType
import cn.langya.sun.utils.Utils
import org.lwjgl.input.Keyboard

/**
 * @author LangYa
 * @ClassName Module
 * @date 2023/12/14 21:02
 * @Version 1.0
 */

open class Module(val name: String, val array: Boolean,val category: Category): Utils() {

                  /*
    var keyBind = key
        set(value) {
            field = value
            if (Keyboard.isKeyDown(field)) {
                state = !state
            }
        }

                   */


    // Current state of module
    var state = false
        set(value) {
            if (field == value) return

            Sun.uiManager!!.addNotification(
                "Notifications",
                "${if (value) "Enabled " else "Disabled "}$name",
                if (value) NotificationType.SUCCESS else NotificationType.ERROR
            )

            // Call on enabled or disabled
            if (value) {
                onEnable()
            }

            field = value

        }

    open fun onUpdate() {}

    open fun onEnable() {}

    open fun onDisable() {}

}