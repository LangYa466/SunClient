package cn.langya.sun.ui

import cn.langya.sun.utils.Utils
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11
import java.awt.Color


object FontManager: Utils() {

    val height = mc.fontRendererObj.FONT_HEIGHT

    fun drawString(text: String, x: Int, y: Int, color: Color) {
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.enableTexture2D()
        GlStateManager.enableAlpha()
        GlStateManager.enablePolygonOffset()
        GlStateManager.doPolygonOffset(-1f, -1f)

        mc.fontRendererObj.drawString(text, x, y, color.rgb)

        GlStateManager.disablePolygonOffset()
        GlStateManager.disableAlpha()
        GlStateManager.disableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawString(text: String, x: Int, y: Int, color: Int) {
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.enableTexture2D()
        GlStateManager.enableAlpha()
        GlStateManager.enablePolygonOffset()
        GlStateManager.doPolygonOffset(-1f, -1f)

        mc.fontRendererObj.drawString(text, x, y, color)

        GlStateManager.disablePolygonOffset()
        GlStateManager.disableAlpha()
        GlStateManager.disableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawStringWithShadow(text: String, x: Int, y: Int, color: Color) {
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.enableTexture2D()
        GlStateManager.enableAlpha()
        GlStateManager.enablePolygonOffset()
        GlStateManager.doPolygonOffset(-1f, -1f)

        mc.fontRendererObj.drawStringWithShadow(text, x.toFloat(), y.toFloat(), color.rgb)

        GlStateManager.disablePolygonOffset()
        GlStateManager.disableAlpha()
        GlStateManager.disableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawStringWithShadow(text: String, x: Float, y: Float, color: Int) {
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.enableTexture2D()
        GlStateManager.enableAlpha()
        GlStateManager.enablePolygonOffset()
        GlStateManager.doPolygonOffset(-1f, -1f)

        mc.fontRendererObj.drawStringWithShadow(text, x, y, color)

        GlStateManager.disablePolygonOffset()
        GlStateManager.disableAlpha()
        GlStateManager.disableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawCenteredTextScaled(text: String?, givenX: Int, givenY: Int, color: Int, givenScale: Double) {
        GL11.glPushMatrix()
        GL11.glTranslated(givenX.toDouble(), givenY.toDouble(), 0.0)
        GL11.glScaled(givenScale, givenScale, givenScale)
        drawStringWithShadow(text!!, 0.0f, 0.0f, color)
        GL11.glPopMatrix()
    }

    fun drawCenteredString(text: String?, x: Float, y: Float, color: Int) {
        drawString(text!!, (x - (this.getStringWidth(text) shr 1)).toInt(), y.toInt(), color)
    }


    fun getStringWidth(text: String): Int {
        return mc.fontRendererObj.getStringWidth(text)
    }


}
