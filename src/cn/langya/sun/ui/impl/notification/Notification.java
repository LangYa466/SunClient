package cn.langya.sun.ui.impl.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private final String title;
    private final String content;
    private final NotificationType type;
    private boolean isShow;

    public Notification(String title, String content, NotificationType type) {
        this.title = title;
        this.content = content;
        this.type = type;
        isShow = true;
    }

}
