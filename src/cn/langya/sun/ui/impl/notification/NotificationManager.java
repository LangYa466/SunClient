package cn.langya.sun.ui.impl.notification;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.TimeUtil;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtil;
import cn.langya.sun.utils.render.ShadowUtil;
import com.cubk.event.annotations.EventTarget;
import lombok.Getter;
import net.minecraft.client.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class NotificationManager {

    private final CopyOnWriteArrayList<Notification> notifications;

    public NotificationManager() {
        notifications = new CopyOnWriteArrayList<>();
        Sun.eventManager.register(this);
    }

    public void add(String title, String content, NotificationType type) {
        Notification notification = new Notification(title, content, type);
        notifications.add(notification);

        /*
        ClientUtils.loginfo("[Notification] " + notification.getType() + ": " +
                notification.getTitle() + " - " + notification.getContent());
         */
    }

    private static void drawNotification(String title, String content, NotificationType type) {

        if (type == NotificationType.INFO) {
            RoundedUtil.drawRound(0, 1, 10 + FontManager.T20.getStringWidth(content), 2, 2, true, new Color(0, 0, 0, 180));
        }
        if (type == NotificationType.WARING) {
            RoundedUtil.drawRound(0, 1, 10 + FontManager.T20.getStringWidth(content), 2, 2, true, new Color(0, 180, 180, 80));
        }
        if (type == NotificationType.ERROR) {
            RoundedUtil.drawRound(0, 1, 10 + FontManager.T20.getStringWidth(content), 2, 2, true, new Color(180, 0, 0, 80));
        }
        if (type == NotificationType.SUCCESS) {
            RoundedUtil.drawRound(0, 1, 10 + FontManager.T20.getStringWidth(content), 2, 2, true, new Color(0, 0, 180, 80));
        }

        RoundedUtil.drawRound(0, 0, 10 + FontManager.T20.getStringWidth(content), 30, 2, true, new Color(0, 0, 0, 80));
        RenderUtil.resetColor();
        ShadowUtil.drawShadow(0, 0, 10 + FontManager.T20.getStringWidth(content), 30);
        FontManager.T20.drawString(title, 3, 3, -1);
        FontManager.T20.drawString(content, 3, 10, -1);
    }

    @EventTarget
    void onR2D(EventRender2D e) {
        for (final Notification notification : notifications) {

            drawNotification(notification.getTitle(), notification.getContent(), notification.getType());


            Runnable task = () -> {
                TimeUtil t = new TimeUtil();
                if (t.hasTimePassed(5000)) {
                    notification.setShow(false);
                }
            };

            new Thread(task).start();

            notifications.removeIf(notifications -> !notification.isShow());

        }


    }
}
