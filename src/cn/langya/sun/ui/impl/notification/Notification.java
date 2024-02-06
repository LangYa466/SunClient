package cn.langya.sun.ui.impl.notification;


import lombok.Getter;

@Getter
public class Notification {
    private final String title;
    private final String content;
    private final NotificationType type;

    public Notification(String title, String content, NotificationType type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

}
