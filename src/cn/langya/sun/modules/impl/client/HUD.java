package cn.langya.sun.modules.impl.client;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.ui.FontManager;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.values.ColorValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.text.TextFormatting;

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
        add(animationSpeed, mainColor);
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if (!isEnabled()) return;

        // skid logo
        final String str = TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + mc.player.getName() + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + Minecraft.getDebugFPS() + "fps" + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + (HUD.mc.isSingleplayer() ? "SinglePlayer" : HUD.mc.getCurrentServerData().serverIP);
        RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.getStringWidth(str) + 8 + FontManager.getStringWidth(Sun.name.toUpperCase())) + 5, 15.0f, new Color(19, 19, 19, 230).getRGB());
        RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.getStringWidth(str) + 8 + FontManager.getStringWidth(Sun.name.toUpperCase())) + 5, 1.0f, color(8).getRGB());
        FontManager.drawString(str, (int) (11 + FontManager.getStringWidth(Sun.name.toUpperCase())), (int) 7.5f, Color.WHITE.getRGB());
        FontManager.drawString(Sun.name.toUpperCase(), (int) 10.0f, (int) 7.5f, Color.WHITE.getRGB());

        // info
        FontManager.drawStringWithShadow("X" + (int) mc.player.posX + "Y" + (int) mc.player.posY + "Z:" + (int) mc.player.posZ, 3F, RenderUtil.getHeight() - (3F + FontManager.height), -1);
        FontManager.drawStringWithShadow("FPS:" + mc.getDebugFPS(), 3F, RenderUtil.getHeight() - (3F + FontManager.height) * 2, -1);
    }


    public static Color color(final int tick) {
        Color textColor = new Color(-1);
        textColor = fade(5, tick * 20, new Color(HUD.mainColor.getColor()), 1.0f);
        return textColor;
    }

    public static Color fade(final int speed, final int index, final Color color, final float alpha) {
        final float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
        angle = ((angle > 180) ? (360 - angle) : angle) + 180;
        final Color colorHSB = new Color(Color.HSBtoRGB(hsb[0], hsb[1], angle / 360.0f));
        return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(0, Math.min(255, (int)(alpha * 255.0f))));
    }


}