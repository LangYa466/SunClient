package cn.langya.sun.ui.impl;

import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.ui.UI;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtil;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

/**
 * @author LangYa
 * @date 2024/2/3 ÏÂÎç 04:01
 */

public class Test extends UI {


    public Test() {
        super("Test");
        setHeight(50);
        setWidth(50);
        setX(50);
        setY(50);
    }


    @Override
    public void draw() {
        RoundedUtil.drawRound(50,50,50,50,0, Color.WHITE);
        RenderUtil.resetColor();
    }
}
