package cn.langya.sun.ui;

import cn.langya.sun.utils.Utils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class FontManager extends Utils {

    public static final int height = mc.fontRendererObj.FONT_HEIGHT;

    public static void drawString(String text, int x, int y, Color color) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(-1f, -1f);

        mc.fontRendererObj.drawString(text, x, y, color.getRGB());

        GlStateManager.disablePolygonOffset();
        GlStateManager.disableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawString(String text, int x, int y, int color) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(-1f, -1f);

        mc.fontRendererObj.drawString(text, x, y, color);

        GlStateManager.disablePolygonOffset();
        GlStateManager.disableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawStringWithShadow(String text, int x, int y, Color color) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(-1f, -1f);

        mc.fontRendererObj.drawStringWithShadow(text, (float) x, (float) y, color.getRGB());

        GlStateManager.disablePolygonOffset();
        GlStateManager.disableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawStringWithShadow(String text, float x, float y, int color) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(-1f, -1f);

        mc.fontRendererObj.drawStringWithShadow(text, x, y, color);

        GlStateManager.disablePolygonOffset();
        GlStateManager.disableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawCenteredTextScaled(String text, int givenX, int givenY, int color, double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated(givenX, givenY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        drawStringWithShadow(text, 0.0f, 0.0f, color);
        GL11.glPopMatrix();
    }

    public static void drawCenteredString(String text, float x, float y, int color) {
        drawString(text, (int) (x - (getStringWidth(text) >> 1)), (int) y, color);
    }

    public static int getStringWidth(String text) {
        return mc.fontRendererObj.getStringWidth(text);
    }
}
