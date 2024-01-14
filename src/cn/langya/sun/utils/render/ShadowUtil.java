package cn.langya.sun.utils.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

public class ShadowUtil {
    public static int deltaTime = 0;

    public static void drawShadow(float x, float y, float width, float height) {
        drawShadowWithCustomAlpha(x,y,width,height,255f);
    }

        public static void drawShadowWithCustomAlpha(float x, float y, float width, float height, float alpha) {
        drawTexturedRectWithCustomAlpha(x - 9, y - 9, 9f, 9f, "paneltopleft", alpha);
        drawTexturedRectWithCustomAlpha(x - 9, y + height, 9f, 9f, "panelbottomleft", alpha);
        drawTexturedRectWithCustomAlpha(x + width, y + height, 9f, 9f, "panelbottomright", alpha);
        drawTexturedRectWithCustomAlpha(x + width, y - 9, 9f, 9f, "paneltopright", alpha);
        drawTexturedRectWithCustomAlpha(x - 9, y, 9f, height, "panelleft", alpha);
        drawTexturedRectWithCustomAlpha(x + width, y, 9f, height, "panelright", alpha);
        drawTexturedRectWithCustomAlpha(x, y - 9, width, 9f, "paneltop", alpha);
        drawTexturedRectWithCustomAlpha(x, y + height, width, 9f, "panelbottom", alpha);
    }

    public static void drawTexturedRectWithCustomAlpha(float x, float y, float width, float height, String image, float alpha) {
        glPushMatrix();
        boolean enableBlend = glIsEnabled(GL_BLEND);
        boolean disableAlpha = !glIsEnabled(GL_ALPHA_TEST);
        if (!enableBlend) glEnable(GL_BLEND);
        if (!disableAlpha) glDisable(GL_ALPHA_TEST);
        GlStateManager.color(1f, 1f, 1f, alpha);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("shaders/" + image + ".png"));
        drawModalRectWithCustomSizedTexture(
                x,
                y,
                0F,
                0F,
                width,
                height,
                width,
                height
        );
        if (!enableBlend) glDisable(GL_BLEND);
        if (!disableAlpha) glEnable(GL_ALPHA_TEST);
        GlStateManager.resetColor();
        glPopMatrix();
    }

    public static void drawModalRectWithCustomSizedTexture(
            float x,
            float y,
            float u,
            float v,
            float width,
            float height,
            float textureWidth,
            float textureHeight
    ) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + width) * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }
}