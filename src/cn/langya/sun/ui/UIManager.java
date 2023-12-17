package cn.langya.sun.ui;

import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.ui.impl.notification.NotificationType;

public class UIManager {

    private int x;
    private int y;
    private int width;
    private int height;

    public UIManager(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addNotification(String title, String content, NotificationType type) {
     //   NotificationManager.add(title, content, type);
    }

    public boolean hover(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
