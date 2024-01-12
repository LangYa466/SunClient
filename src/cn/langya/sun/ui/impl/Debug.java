// by paimon
package cn.langya.sun.ui.impl;

import java.awt.*;

import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.ui.FontManager;
import cn.langya.sun.ui.UiModule;
import cn.langya.sun.utils.render.RenderUtil;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.entity.*;


public class Debug extends UiModule
{
    public Debug() {
        super("Debug", 20.0, 60.0, 150.0, 200.0);
    }
    
    @EventTarget
    public void onRender2D(final Render2DEvent event) {
        final EntityPlayerSP player = Debug.mc.player;
        double x = this.posX * RenderUtil.getWidth();
        double y = this.posY * RenderUtil.getHeight();
        RenderUtil.drawRect(x, y, x + 120.0, y + 200.0, new Color(20, 20, 20, 180).getRGB());
        x += 5.0;
        y -= 5.0;
        FontManager.drawStringWithShadow("Health: " + this.toFloat(player.getHealth()), (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("X:" + this.toFloat(player.posX) + " Y:" + this.toFloat(player.posY) + " Z:" + this.toFloat(player.posZ), (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("Motion X:" + this.toDouble(player.motionX) + " Y:" + this.toDouble(player.motionY) + " Z:" + this.toDouble(player.motionZ), (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("Hurt Time: " + player.hurtTime, (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("Hurt ResistantTime Time: " + player.hurtResistantTime, (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("Yaw: " + this.toFloat(player.rotationYaw) + " Pitch" + this.toFloat(player.rotationPitch), (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
        FontManager.drawStringWithShadow("Head: " + this.toFloat(player.rotationYawHead) + " Body: " + this.toFloat(player.renderYawOffset), (float) x, (float) (y += 10.0), new Color(255, 255, 255).getRGB());
    }
    
    private float toFloat(final double value) {
        return (int)value + (int)(value * 10.0) % 10 / 10.0f;
    }
    
    private float toDouble(final double value) {
        return (int)value + (int)(value * 100.0) % 100 / 100.0f;
    }
}
