package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.Utils;
import cn.langya.sun.values.AbstractValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module extends Utils {

    @Getter
    public final String name;
    @Getter
    public int keyCode;
    @Getter
    public String tag;
    @Getter
    public boolean array;
    @Getter
    public final Category category;
    public boolean state;
    @Getter
    private List<AbstractValue> values = new ArrayList<>();
    public void add(AbstractValue... value) {
        values = Arrays.asList(value);
    }
    public Module(String name, Category category) {
        this.name = name;
        this.tag = "";
        this.array = true;
        this.category = category;
        this.keyCode = 0;
    }

    public Module(String name, Boolean array, Category category) {
        this.name = name;
        this.tag = "";
        this.array = array;
        this.category = category;
        this.keyCode = 0;
    }
    public Module(String name, Category category, int keyCode) {
        this.name = name;
        this.tag = "";
        this.array = true;
        this.category = category;
        this.keyCode = keyCode;
    }

    public Module(String name, Boolean array, Category category, int keyCode) {
        this.name = name;
        this.tag = "";
        this.array = array;
        this.category = category;
        this.keyCode = keyCode;
    }

    public boolean isEnabled() {
        return state;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setSuffix(String tag) {
        this.tag = tag;
    }
    public void toggle(){
        setState(!state);
    }

    public void setState(boolean value) {
        if (state == value) return;

        Sun.notificationManager.add("Module",name + " has " + (value ? " Enabled" : " Disabled"),(value ? NotificationType.SUCCESS : NotificationType.ERROR));

        if (value) {
            Sun.eventManager.register(this);
            onEnable();
        } else {
            Sun.eventManager.unregister(this);
            onDisable();
        }

        state = value;
    }

    public void onEnable() {}

    public void onDisable() {}
}