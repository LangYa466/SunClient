package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.Utils;
import org.lwjgl.input.Keyboard;

public class Module extends Utils {

    private final String name;
    private final boolean array;
    private final Category category;

    /*
    private int keyBind;

    public void setKeyBind(int key) {
        keyBind = key;
        if (Keyboard.isKeyDown(keyBind)) {
            state = !state;
        }
    }

     */

    // Current state of module
    private boolean state;

    public Module(String name, boolean array, Category category) {
        this.name = name;
        this.array = array;
        this.category = category;
    }

    public String getName() {
        return name;
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

    public void onUpdate() {}

    public void onEnable() {}

    public void onDisable() {}
}