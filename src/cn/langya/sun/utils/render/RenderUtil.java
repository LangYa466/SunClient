package cn.langya.sun.utils.render;

import cn.langya.sun.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.awt.*;
import java.text.NumberFormat;

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

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, (float)((double)limit * 0.01));
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
    public static void drawArc(float n, float n2, double n3, final int n4, final int n5, final double n6, final int n7) {
        n3 *= 2.0;
        n *= 2.0f;
        n2 *= 2.0f;
        final float n8 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n4 & 0xFF) / 255.0f;
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glLineWidth((float) n7);
        GL11.glEnable(2848);
        GL11.glColor4f(n9, n10, n11, n8);
        GL11.glBegin(3);
        int n12 = n5;
        while (n12 <= n6) {
            GL11.glVertex2d(n + Math.sin(n12 * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(n12 * 3.141592653589793 / 180.0) * n3);
            ++n12;
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
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


    public static void drawRound(float x, float y, float width, float height, float radius, int color) {
        RenderUtil.drawGoodCircle(x + radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + radius, y + height - radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + height - radius, radius, color);
        Gui.drawRect3(x + radius, y, width - radius * 2.0f, height, color);
        Gui.drawRect3(x, y + radius, width, height - radius * 2.0f, color);
    }
    public static void drawGoodCircle(double x, double y, float radius, int color) {
        RenderUtil.color(color);
        GLUtil.setup2DRendering(() -> {
            GL11.glEnable(2832);
            GL11.glHint(3153, 4354);
            GL11.glPointSize(radius * (float)(2 * Minecraft.getMinecraft().gameSettings.guiScale));
            GLUtil.render(0, () -> GL11.glVertex2d(x, y));
        });
    }
    public static void drawOutline(float x, float y, float x2, float y2, float radius, float line, float offset, Color c1, Color c2) {
        double angleRadians;
        int i;
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glBlendFunc(770, 771);
        GL11.glPushMatrix();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        float edgeRadius = radius;
        float centerX = x + edgeRadius;
        float centerY = y + edgeRadius;
        int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        int colorI = 0;
        centerX = x2;
        centerY = y2 + edgeRadius;
        vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        for (i = 0; i <= vertices; ++i) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            angleRadians = Math.PI * 2 * (double)i / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
            ++colorI;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x2 + edgeRadius;
        centerY = y2 + edgeRadius;
        i = 0;
        while ((float)i <= y2 - y) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            GL11.glVertex2d(centerX, centerY - (float)i);
            ++colorI;
            ++i;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x2;
        centerY = y + edgeRadius;
        for (i = 0; i <= vertices; ++i) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            angleRadians = Math.PI * 2 * (double)(i + 90) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
            ++colorI;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x2;
        centerY = y;
        i = 0;
        while ((float)i <= x2 - x) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            GL11.glVertex2d(centerX - (float)i, centerY);
            ++colorI;
            ++i;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x;
        centerY = y + edgeRadius;
        for (i = 0; i <= vertices; ++i) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            angleRadians = Math.PI * 2 * (double)(i + 180) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
            ++colorI;
        }
        colorI = 0;
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x2;
        centerY = y2 + (float)vertices + offset;
        i = 0;
        while ((float)i <= x2 - x) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            GL11.glVertex2d(centerX - (float)i, centerY);
            ++colorI;
            ++i;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x;
        centerY = y2 + edgeRadius;
        for (i = 0; i <= vertices; ++i) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            angleRadians = Math.PI * 2 * (double)(i + 180) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY - Math.cos(angleRadians) * (double)edgeRadius);
            ++colorI;
        }
        GL11.glEnd();
        GL11.glLineWidth(line);
        GL11.glBegin(3);
        centerX = x - edgeRadius;
        centerY = y2 + edgeRadius;
        i = 0;
        while ((float)i <= y2 - y) {
            RenderUtil.color(RenderUtil.fadeBetween(c1.getRGB(), c2.getRGB(), 20L * (long)colorI));
            GL11.glVertex2d(centerX, centerY - (float)i);
            ++colorI;
            ++i;
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    public static int fadeBetween(int startColour, int endColour, double progress) {
        if (progress > 1.0) {
            progress = 1.0 - progress % 1.0;
        }
        return RenderUtil.fadeTo(startColour, endColour, progress);
    }

    public static int fadeTo(int startColour, int endColour, double progress) {
        double invert = 1.0 - progress;
        int r = (int)((double)(startColour >> 16 & 0xFF) * invert + (double)(endColour >> 16 & 0xFF) * progress);
        int g = (int)((double)(startColour >> 8 & 0xFF) * invert + (double)(endColour >> 8 & 0xFF) * progress);
        int b = (int)((double)(startColour & 0xFF) * invert + (double)(endColour & 0xFF) * progress);
        int a = (int)((double)(startColour >> 24 & 0xFF) * invert + (double)(endColour >> 24 & 0xFF) * progress);
        return (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }
    public static void drawRect(double left, double top, double right, double bottom, int color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        double minX = Math.min(left, right);
        double maxX = Math.max(left, right);
        double minY = Math.min(top, bottom);
        double maxY = Math.max(top, bottom);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(minX, maxY, 0.0).endVertex();
        worldrenderer.pos(maxX, maxY, 0.0).endVertex();
        worldrenderer.pos(maxX, minY, 0.0).endVertex();
        worldrenderer.pos(minX, minY, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
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

    public static void drawImage(ResourceLocation image, float x, float y, int width, int height) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(image);
        float f = 1.0f / (float)width;
        float f2 = 1.0f / (float)height;
        BufferBuilder worldrenderer = Tessellator.getInstance().getBuffer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + (float)height, 0.0).tex(0.0f * f, (float)height * f2).endVertex();
        worldrenderer.pos(x + (float)width, y + (float)height, 0.0).tex((float)width * f, (float)height * f2).endVertex();
        worldrenderer.pos(x + (float)width, y, 0.0).tex((float)width * f, 0.0f * f2).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(0.0f * f, 0.0f * f2).endVertex();
        Tessellator.getInstance().draw();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void drawImage(ResourceLocation imageLocation, double x, double y, double width, double height, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableAlpha();
        mc.getTextureManager().bindTexture(imageLocation);
        RenderUtil.glColor(color);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, 0.0f, 0.0f, (int) width, (int) height, (float) width, (float) height);
        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        drawImage(image, x, y, width, height, Color.white.getRGB());
    }

    public static void color(int color) {
        GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)(color >> 24 & 0xFF));
    }

    public static void drawBigHead(final float x, final float y, final float width, final float height, final AbstractClientPlayer player) {
        final double offset = -(player.hurtTime * 23);
        RenderUtil.glColor(new Color(255, (int)(255.0 + offset), (int)(255.0 + offset)).getRGB());
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        mc.getTextureManager().bindTexture(player.getLocationSkin());
        Gui.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    public static void drawHGradientRect(double x, double y, double width, double height, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GLUtil.setup2DRendering(() -> {
            GL11.glShadeModel(7425);
            final BufferBuilder worldrenderer = Tessellator.getInstance().getBuffer();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldrenderer.pos(x, y, 0.0).color(f1, f2, f3, f).endVertex();
            worldrenderer.pos(x, y + height, 0.0).color(f1, f2, f3, f).endVertex();
            worldrenderer.pos(x + width, y + height, 0.0).color(f5, f6, f7, f4).endVertex();
            worldrenderer.pos(x + width, y, 0.0).color(f5, f6, f7, f4).endVertex();
            Tessellator.getInstance().draw();
            GlStateManager.resetColor();
            GL11.glShadeModel(7424);
        });
    }

    public static int[] getFractionIndicies(final float[] fractions, final float progress) {
        final int[] range = new int[2];
        int startPoint;
        for (startPoint = 0; startPoint < fractions.length && fractions[startPoint] <= progress; ++startPoint) {}
        if (startPoint >= fractions.length) {
            startPoint = fractions.length - 1;
        }
        range[0] = startPoint - 1;
        range[1] = startPoint;
        return range;
    }

    public static Color blend(final Color color1, final Color color2, final double ratio) {
        final float r = (float)ratio;
        final float ir = 1.0f - r;
        final float[] rgb1 = new float[3];
        final float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        float red = rgb1[0] * r + rgb2[0] * ir;
        float green = rgb1[1] * r + rgb2[1] * ir;
        float blue = rgb1[2] * r + rgb2[2] * ir;
        if (red < 0.0f) {
            red = 0.0f;
        }
        else if (red > 255.0f) {
            red = 255.0f;
        }
        if (green < 0.0f) {
            green = 0.0f;
        }
        else if (green > 255.0f) {
            green = 255.0f;
        }
        if (blue < 0.0f) {
            blue = 0.0f;
        }
        else if (blue > 255.0f) {
            blue = 255.0f;
        }
        Color color3 = null;
        try {
            color3 = new Color(red, green, blue);
        }
        catch (IllegalArgumentException exp) {
            final NumberFormat nf = NumberFormat.getNumberInstance();
            System.out.println(nf.format(red) + "; " + nf.format(green) + "; " + nf.format(blue));
            exp.printStackTrace();
        }
        return color3;
    }


    public static Color blendColors(final float[] fractions, final Color[] colors, final float progress) {
        if (fractions == null) {
            throw new IllegalArgumentException("Fractions can't be null");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colours can't be null");
        }
        if (fractions.length == colors.length) {
            final int[] indicies = getFractionIndicies(fractions, progress);
            final float[] range = { fractions[indicies[0]], fractions[indicies[1]] };
            final Color[] colorRange = { colors[indicies[0]], colors[indicies[1]] };
            final float max = range[1] - range[0];
            final float value = progress - range[0];
            final float weight = value / max;
            final Color color = blend(colorRange[0], colorRange[1], 1.0f - weight);
            return color;
        }
        throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
    }

    public static void renderRoundedRect(float x, float y, float width, float height, float radius, int color) {
        RenderUtil.drawGoodCircle(x + radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + radius, y + height - radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + height - radius, radius, color);
        Gui.drawRect3(x + radius, y, width - radius * 2.0f, height, color);
        Gui.drawRect3(x, y + radius, width, height - radius * 2.0f, color);
    }


}
