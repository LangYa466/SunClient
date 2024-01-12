// by paimon
package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.*;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.utils.render.RenderUtil;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UiManager
{
    public final List<UiModule> UiModules;
    private final Minecraft mc;
    public double mouseX;
    public double mouseY;
    
    public UiManager() {
        UiModules = new ArrayList<UiModule>();
        mc = Minecraft.getMinecraft();
        Sun.eventManager.register(this);
        System.out.println("Init UiModules...");
        addModule(new Debug());
    }
    
    @EventTarget
    public void moveUi(final Render2DEvent event) {
        if (!mc.ingameGUI.getChatGUI().getChatOpen()) {
            return;
        }
        for (final UiModule m : UiModules) {
            if (!m.getState()) {
                continue;
            }
            final double xpos = m.getPosX();
            final double ypos = m.getPosY();
            final double mwidth = m.getWidth();
            final double mheight = m.getHeight();
            final double mousex = mouseX;
            final double mousey = mouseY;
            if (mousex > xpos && mousey > ypos && mousex < xpos + mwidth && mousey < ypos + mheight && Mouse.isButtonDown(0)) {
                RenderUtil.drawBorderedRect((float)xpos, (float)ypos, (float)(xpos + mwidth), (float)(ypos + mheight), 2.0f, new Color(225, 225, 225).getRGB(), 0);
                if (m.moveX == 0.0 && m.moveY == 0.0) {
                    m.moveX = (float)mouseX - xpos;
                    m.moveY = (float)mouseY - ypos;
                }
                else {
                    double setX = mouseX - m.moveX;
                    double setY = mouseY - m.moveY;
                    setX = Math.min(Math.max(0.0, setX), RenderUtil.getWidth() - m.width);
                    setY = Math.min(Math.max(0.0, setY), RenderUtil.getHeight() - m.height);
                    m.setPosX(setX);
                    m.setPosY(setY);
                }
            }
            else {
                if (m.moveX == 0.0 && m.moveY == 0.0) {
                    continue;
                }
                m.moveX = 0.0;
                m.moveY = 0.0;
            }
        }
    }
    
    public void addModule(final UiModule module) {
        for (final Field field : module.getClass().getDeclaredFields()) {
            field.setAccessible(true);
        }
        UiModules.add(module);
    }
    
    public UiModule getModule(final String name) {
        for (final UiModule m : UiModules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public UiModule getModule(final Class<?> cls) {
        for (final UiModule m : UiModules) {
            if (m.getClass() == cls) {
                return m;
            }
        }
        return null;
    }
    
    public List<UiModule> getModules() {
        return UiModules;
    }
}
