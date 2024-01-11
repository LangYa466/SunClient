package cn.langya.sun.utils.render;

import cn.langya.sun.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author LangYa
 * @ClassName RenderUtils
 * @date 2023/12/17 21:00
 * @Version 1.0
 */

public class RenderUtil extends Utils {

    public final static ResourceLocation backgroundTexture = mc.getTextureManager().getDynamicTextureLocation("background", new DynamicTexture(256, 256));

    public static int getWidth() {
        return new ScaledResolution(mc).getScaledWidth();
    }

    public static int getHeight() {
        return new ScaledResolution(mc).getScaledHeight();
    }

        public static Color originalRainbow(long offset) {
            Color currentColor = new Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 1.0F, 1.0F));
            return new Color(
                    currentColor.getRed() / 255F * 1F,
                    currentColor.getGreen() / 255F * 1F,
                    currentColor.getBlue() / 255F * 1F,
                    currentColor.getAlpha() / 255F
            );
        }

    public static void glColor(int hex) {
        float alpha = ((hex >> 24) & 0xFF) / 255f;
        float red = ((hex >> 16) & 0xFF) / 255f;
        float green = ((hex >> 8) & 0xFF) / 255f;
        float blue = (hex & 0xFF) / 255f;
        GlStateManager.color(red, green, blue, alpha);
    }

    public static Color getColor(int color) {
        int f = (color >> 24) & 0xFF;
        int f2 = (color >> 16) & 0xFF;
        int f3 = (color >> 8) & 0xFF;
        int f4 = color & 0xFF;
        return new Color(f2, f3, f4, f);
    }

    public static void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
    }

    public static void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, y, 0.0).endVertex();
        for (int i = (int) ((startAngle / 360.0) * 100); i <= (int) ((endAngle / 360.0) * 100); i++) {
            double angle = Math.PI * 2 * i / 100 + Math.toRadians(180.0);
            worldRenderer.pos(x + sin(angle) * radius, y + cos(angle) * radius, 0.0).endVertex();
        }
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0f;
        float f2 = (color >> 16 & 0xFF) / 255.0f;
        float f3 = (color >> 8 & 0xFF) / 255.0f;
        float f4 = (color & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f1);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width) {
        int i = 18;
        int j = 90 / i;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x2, y + radius);
        GL11.glVertex2f(x2, y2 - radius);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x2 - radius, y2 - 0.1f);
        GL11.glVertex2f(x + radius, y2 - 0.1f);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x + 0.1f, y2 - radius);
        GL11.glVertex2f(x + 0.1f, y + radius);
        GL11.glEnd();
        float f1 = x2 - radius;
        float f2 = y + radius;
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int k = 0; k <= i; k++) {
            int m = 90 - k * j;
            GL11.glVertex2f(f1 + radius * getRightAngle(m), f2 - radius * getAngle(m));
        }
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int k = 0; k <= i; k++) {
            int m = k * j + 270;
            GL11.glVertex2f(f1 + radius * getRightAngle(m), f2 - radius * getAngle(m));
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; k++) {
            int m = k * j + 90;
            GL11.glVertex2f(f1 + radius * getRightAngle(m), f2 + radius * getAngle(m));
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; k++) {
            int m = 270 - k * j;
            GL11.glVertex2f(f1 + radius * getRightAngle(m), f2 + radius * getAngle(m));
        }
        GL11.glEnd();
        GL11.glLineWidth(1.0f);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left > right) {
            double cacheRight = right;
            right = left;
            left = cacheRight;
        }
        if (top > bottom) {
            double cacheBottom = bottom;
            bottom = top;
            top = cacheBottom;
        }
        float f3 = (float) ((color >> 24) & 255) / 255.0f;
        float f = (float) ((color >> 16) & 255) / 255.0f;
        float f1 = (float) ((color >> 8) & 255) / 255.0f;
        float f2 = (float) (color & 255) / 255.0f;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f, f1, f2, f3);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(left, bottom);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glVertex2d(left, top);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawBorderedRect(float x, float y, float x2, float y2, float width, int color1, int color2) {
        drawRect(x, y, x2, y2, color2);
        drawBorder(x, y, x2, y2, width, color1);
    }

    public static void drawBorder(float x, float y, float x2, float y2, float width, int color1) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        glColor(color1);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    private static float getRightAngle(int angle) {
        return (float) Math.cos(Math.toRadians(angle));
    }

    private static float getAngle(int angle) {
        return (float) Math.sin(Math.toRadians(angle));
    }

    public static void drawTriangle(double x, double y, double x1, double y1, double x2, double y2, int color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        RenderUtil.glColor(color);
        GL11.glBegin(4);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        BufferBuilder tessellator = Tessellator.getInstance().getBuffer();
        tessellator.begin(7, DefaultVertexFormats.POSITION_TEX);
        tessellator.pos(x, y + height, 0.0).tex(u * f, (v + height) * f1).endVertex();
        tessellator.pos(x + width, y + height, 0.0).tex((u + width) * f, (v + height) * f1).endVertex();
        tessellator.pos(x + width, y, 0.0).tex((u + width) * f, v * f1).endVertex();
        tessellator.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.finishDrawing();
    }

    public static void drawTexturedRect(float x, float y, float width, float height, ResourceLocation image, int color) {
        boolean disableAlpha;
        GL11.glPushMatrix();
        boolean enableBlend = GL11.glIsEnabled(3042);
        boolean bl = disableAlpha = !GL11.glIsEnabled(3008);
        if (!enableBlend) {
            GL11.glEnable(3042);
        }
        if (!disableAlpha) {
            GL11.glDisable(3008);
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        RenderUtil.glColor(color);
        RenderUtil.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        if (!enableBlend) {
            GL11.glDisable(3042);
        }
        if (!disableAlpha) {
            GL11.glEnable(3008);
        }
        GL11.glPopMatrix();
    }

    public static void drawShadow(float x, float y, float x2, float y2) {
        RenderUtil.drawTexturedRect(x - 9.0f, y - 9.0f, 9.0f, 9.0f, new ResourceLocation("shaders/paneltopleft.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x - 9.0f, y2, 9.0f, 9.0f, new ResourceLocation("shaders/panelbottomleft.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x2, y2, 9.0f, 9.0f, new ResourceLocation("shaders/panelbottomright.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x2, y - 9.0f, 9.0f, 9.0f, new ResourceLocation("shaders/paneltopright.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x - 9.0f, y, 9.0f, y2 - y, new ResourceLocation("shaders/panelleft.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x2, y, 9.0f, y2 - y, new ResourceLocation("shaders/panelright.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x, y - 9.0f, x2 - x, 9.0f, new ResourceLocation("shaders/paneltop.png"), Color.white.getRGB());
        RenderUtil.drawTexturedRect(x, y2, x2 - x, 9.0f, new ResourceLocation("shaders/panelbottom.png"), Color.white.getRGB());
    }

}
