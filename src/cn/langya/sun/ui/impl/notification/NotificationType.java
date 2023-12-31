package cn.langya.sun.ui.impl.notification;

import java.awt.*;

public enum NotificationType {
    SUCCESS(new Color(0x60E092)),
    ERROR(new Color(0xFF2F2F)),
    WARNING(new Color(0xF5FD00)),
    INFO(new Color(0x6490A7));

    private final Color renderColor;

    NotificationType(Color renderColor) {
        this.renderColor = renderColor;
    }

    public Color getRenderColor() {
        return renderColor;
    }
}