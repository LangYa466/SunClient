package cn.langya.sun.ui

import cn.langya.sun.ui.impl.notification.NotificationManager
import cn.langya.sun.ui.impl.notification.NotificationType


/**
 * @author LangYa
 * @ClassName UIManager
 * @date 2023/12/14 21:17
 * @Version 1.0
 */

open class UIManager(var x: Int, var y: Int, var width: Int, var height: Int){

    fun addNotification(title: String, content: String, type: NotificationType){
        NotificationManager.add(title, content, type)
    }

    fun hover(mouseX: Int, mouseY: Int): Boolean {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height
    }


}