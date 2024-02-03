package cn.langya.sun.ui;


import cn.langya.sun.Sun;
import cn.langya.sun.utils.Utils;
import cn.langya.sun.values.AbstractValue;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LangYa
 * @date 2024/2/3 ÏÂÎç 03:51
 */

public class UI extends Utils {

    @Getter
    public final String name;

    public boolean state;

    @Setter
    private double x;
    @Setter
    private double y;
    @Setter
    private double witdh;
    @Setter
    private double height;
    @Setter
    private double dragx;
    @Setter
    private double dragy;

    @Getter
    private List<AbstractValue> values = new ArrayList<>();
    public void add(AbstractValue... value) {
        values = Arrays.asList(value);
    }

    public UI(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return state;
    }

    public void toggle(){
        setState(!state);
    }

    public void drag() {
        GlStateManager.translate(dragx,dragy, 0);
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