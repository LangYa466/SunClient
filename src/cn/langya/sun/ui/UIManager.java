package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.misc.EventKeyPress;
import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.ui.impl.Test;
import cn.langya.sun.utils.Utils;
import cn.langya.sun.utils.render.RenderUtil;
import com.cubk.event.annotations.EventTarget;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author LangYa
 * @date 2024/2/3 обнГ 03:50
 */

public class UIManager extends Utils {

    public static ArrayList<UI> uis = new ArrayList<>();

    public UIManager() {
        Sun.eventManager.register(this);
        addUI(new Test());
    }

    public static void addUI(UI ui){
        uis.add(ui);
        Sun.eventManager.register(ui);
    }
    
    @EventTarget
    void onR2D(EventRender2D e) {

        final int mouseX = Mouse.getDX();
        final int mouseY = Mouse.getDY();


        if (!mc.ingameGUI.getChatGUI().getChatOpen()) {
            return;
        }

        for (final UI m : uis) {
            if (!m.state) {
                continue;
            }
            final double xpos = m.getX();
            final double ypos = m.getY();
            final double mwidth = m.getWidth();
            final double mheight = m.getHeight();
            final double mousex = mouseX;
            final double mousey = mouseY;
            if (mousex > xpos && mousey > ypos && mousex < xpos + mwidth && mousey < ypos + mheight && Mouse.isButtonDown(0)) {
                RenderUtil.drawBorderedRect((float) xpos, (float) ypos, (float) (xpos + mwidth), (float) (ypos + mheight), 2.0f, new Color(225, 225, 225).getRGB(), 0);
                if (m.dragx == 0.0 && m.dragy == 0.0) {
                    m.dragx = (float) mouseX - xpos;
                    m.dragy = (float) mouseY - ypos;
                } else {
                    double setX = mouseX - m.dragx;
                    double setY = mouseY - m.dragy;
                    setX = Math.min(Math.max(0.0, setX), sr.getScaledWidth() - m.getWidth());
                    setY = Math.min(Math.max(0.0, setY), sr.getScaledHeight() - m.getHeight());
                    m.setDragx(setX);
                    m.setDragy(setY);
                }
            } else {
                if (m.dragx == 0.0 && m.dragy == 0.0) {
                    continue;
                }
                m.dragx = 0.0;
                m.dragy = 0.0;
            }
        }


    }


}
