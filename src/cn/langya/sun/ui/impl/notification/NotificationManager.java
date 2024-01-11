package cn.langya.sun.ui.impl.notification;

import cn.langya.sun.ui.FontManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.ui.Ui;
import cn.langya.sun.utils.ClientUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager extends Ui {

    public static final List<Notification> notifications = new ArrayList<>();

    public NotificationManager(int x, int y, int width, int height) {
        super("Notification",x, y, width, height);
    }

    public static void add(String title, String content, TrayIcon.MessageType type) throws IOException, AWTException {
        Notification notification = new Notification(title, content, type);
        notifications.add(notification);

        ClientUtils.loginfo("[Notification] " + notification.getType() + ": " +
                notification.getTitle() + " - " + notification.getContent());

        drawNotification(title, content, type);
    }

    private static void drawNotification(String title, String text, TrayIcon.MessageType type) throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage image = ImageIO.read(Main.class.getResourceAsStream("/assets/minecraft/sun/icon128.png"));
        TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);

        trayIcon.displayMessage(title, text, type);
    }
}
