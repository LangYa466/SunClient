package cn.langya.sun.ui.impl.notification;


import java.awt.*;

public class Notification {
    private String title;
    private String content;
    private TrayIcon.MessageType type;

    public Notification(String title, String content, TrayIcon.MessageType type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TrayIcon.MessageType getType() {
        return type;
    }

    public void setType(TrayIcon.MessageType type) {
        this.type = type;
    }

}
