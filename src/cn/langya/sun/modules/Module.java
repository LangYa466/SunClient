package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.Utils;
import cn.langya.sun.values.AbstractValue;

import java.util.ArrayList;
import java.util.List;

public class Module extends Utils {

    public final String name;
    public int keyCode;
    public String tag;
    public final boolean array;
    public final Category category;
    public boolean state;
    private List<AbstractValue> values = new ArrayList<>();
    public void add(AbstractValue value) {
        values.add(value);
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
    public String getName() {
        return name;
    }

    public int getKeyCode() {
        return keyCode;
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

    public boolean isEnabled() {
        return state;
    }

    public List<AbstractValue> getValues() {
        return values;
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