package cn.langya.sun.modules.impl.client;

import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.ColorValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName HUD
 * @date 2024/1/11 下午 02:42
 * @Version 1.0
 */

public class HUD extends Module {
    public static final DoubleValue animationSpeed = new DoubleValue("Animation Speed", 4.0, 10.0, 1.0);
    public static final ColorValue mainColor = new ColorValue("Main Color",Color.white.getRGB());

    public HUD() {
        super("HUD", Category.Render);
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;
    }


}
