package cn.langya.sun.ui.impl.notification;

import cn.langya.sun.ui.FontManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.ui.Ui;
import cn.langya.sun.utils.ClientUtils;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager extends Ui {

    public static final List<Notification> notifications = new ArrayList<>();

    public NotificationManager(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static void add(String title, String content, NotificationType type) {
        Notification notification = new Notification(title, content, type);
        notifications.add(notification);

        ClientUtils.loginfo("[Notification] " + notification.getType() + ": " +
                notification.getTitle() + " - " + notification.getContent());

        drawXylitol(title, content, type);
    }

    private static void drawXylitol(String title, String content, NotificationType type) {
        long nowTime = System.currentTimeMillis();
        long displayTime = System.currentTimeMillis();
        int width = 100;

        Gui.drawRect(3, 0, 5, 27 - 5, new Color(0, 0, 0, 120).getRGB());

        Gui.drawRect(
                3,
                0,
                (int) Math.max(width - width * ((nowTime - displayTime) / (500 * 2 + 1500)) + 5, 0),
                27 - 5,
                type.getRenderColor().getRGB()
        );

        FontManager.drawString(title, 6, 3, Color.white);
        FontManager.drawString(content, 6, 12, Color.white);
    }
}
