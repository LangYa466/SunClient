package cn.langya.sun.ui.impl.notification;

import cn.langya.sun.utils.ClientUtils;
import net.minecraft.client.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationManager {

    public static final List<Notification> notifications = new ArrayList<>();


    public static void add(String title, String content, TrayIcon.MessageType type)  {
        Notification notification = new Notification(title, content, type);
        notifications.add(notification);

        ClientUtils.loginfo("[Notification] " + notification.getType() + ": " +
                notification.getTitle() + " - " + notification.getContent());

        try {
            drawNotification(title, content, type);
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private static void drawNotification(String title, String text, TrayIcon.MessageType type) throws IOException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage image = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/assets/minecraft/sun/icon128.png")));
        TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);

        trayIcon.displayMessage(title, text, type);
    }
}
