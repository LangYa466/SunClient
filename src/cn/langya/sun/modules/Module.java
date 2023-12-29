package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.Utils;

public class Module extends Utils {

    public final String name;
    public final String tag;
    public final boolean array;
    public final Category category;

    public boolean state;

    public Module(String name, String tag, Category category) {
        this.name = name;
        this.tag = tag;
        this.array = true;
        this.category = category;
    }

    public Module(String name, String tag, Boolean array, Category category) {
        this.name = name;
        this.tag = tag;
        this.array = array;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public boolean isArray() {
        return array;
    }

    public Category getCategory() {
        return category;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean value) {
        if (state == value) return;

        Sun.uiManager.addNotification(
                "Notifications",
                (value ? "Enabled " : "Disabled ") + name,
                (value ? NotificationType.SUCCESS : NotificationType.ERROR)
        );

        // Call on enabled or disabled
        if (value) {
            onEnable();
        } else {
            onDisable();
        }

        state = value;
    }

    public void onEnable() {}

    public void onDisable() {}
}