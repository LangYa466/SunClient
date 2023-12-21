package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.event.ListenerAdapter;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.Utils;
import lombok.Getter;
import org.lwjgl.input.Keyboard;

public class Module extends ListenerAdapter {

    @Getter
    private final String name;
    @Getter
    private final boolean array;
    @Getter
    private final Category category;

    // Current state of module
    private boolean state;

    public Module(String name, boolean array, Category category) {
        this.name = name;
        this.array = array;
        this.category = category;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean value) {
        if (state == value) return;

        ClientUtils.loginfo(name + (value ? "-开启 " : "-关闭 "));

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