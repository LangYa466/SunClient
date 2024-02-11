package cn.langya.sun.ui;


import cn.langya.sun.Sun;
import cn.langya.sun.utils.Utils;
import cn.langya.sun.values.AbstractValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LangYa
 * @date 2024/2/3 обнГ 03:51
 */

@Getter
@Setter
public class UI extends Utils {
    public final String name;
    public boolean state;
    private double x;
    private double y;
    private double width;
    private double height;
    protected double dragx;
    protected double dragy;
    
    private List<AbstractValue> values = new ArrayList<>();
    public void add(AbstractValue... value) {
        values = Arrays.asList(value);
    }

    public UI(String name) {
        this.name = name;
        this.dragx = 0.0;
        this.dragy = 0.0;
    }

    public boolean isEnabled() {
        return state;
    }

    public void toggle(){
        setState(!state);
    }

    public static boolean hover(int mouseX, int mouseY, double x, double y, double width, double height) {
        return (mouseX > x && mouseX < (x + width)) && (mouseY > y && mouseY < (y + height));
    }

    public void setState(boolean value) {
        if (state == value) return;

        // Call on enabled or disabled
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