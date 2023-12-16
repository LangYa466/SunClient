package cn.langya.sun.ui.impl.notification

import cn.langya.sun.ui.FontManager
import cn.langya.sun.ui.UIManager
import cn.langya.sun.utils.ClientUtils
import cn.langya.sun.utils.render.RenderUtils
import net.minecraft.client.gui.Gui
import java.awt.Color
import java.util.*
import kotlin.math.max

object NotificationManager: UIManager(0,0,100,22) {

    val notifications: MutableList<Notification> = ArrayList()

    fun add(title: String, content: String, type: NotificationType) {

        val notification = Notification(title, content, type)
        notifications.add(notification)

        ClientUtils.loginfo(
            "[Notification] " + notification.type + ": " +
                    notification.title + " - " + notification.content
        )

        // draw
        drawXylitol(title, content, type)

    }

    private fun drawXylitol(title: String, content: String, type: NotificationType) {

        val nowTime = System.currentTimeMillis()
        val displayTime = System.currentTimeMillis()
        val width = 100

        Gui.drawRect(3, 0, 5, 27 - 5, Color(0, 0, 0, 120).rgb)

        Gui.drawRect(
            3,
            0,
            max(width - width * ((nowTime - displayTime) / (500 * 2 + 1500)) + 5, 0).toInt(),
            27 - 5,
            type.renderColor.rgb
        )

        FontManager.drawString(title, 6, 3, Color.white)
        FontManager.drawString(content, 6, 12, Color.white)
    }


}
