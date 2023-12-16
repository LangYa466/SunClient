package cn.langya.sun.utils.render

import cn.langya.sun.ui.lunar.util.ClientMathUtils
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin


/**
 * @author LangYa
 * @ClassName RenderUtils
 * @date 2023/12/15 16:00
 * @Version 1.0
 */

object RenderUtils {

    fun glColor(color: Color) {
        val red = color.red / 255f
        val green = color.green / 255f
        val blue = color.blue / 255f
        val alpha = color.alpha / 255f
        GlStateManager.color(red, green, blue, alpha)
    }

    fun glColor(hex: Int) {
        val alpha = (hex shr 24 and 0xFF) / 255f
        val red = (hex shr 16 and 0xFF) / 255f
        val green = (hex shr 8 and 0xFF) / 255f
        val blue = (hex and 0xFF) / 255f
        GlStateManager.color(red, green, blue, alpha)
    }

    fun getColor(color: Int): Color {
        val f = color shr 24 and 0xFF
        val f2 = color shr 16 and 0xFF
        val f3 = color shr 8 and 0xFF
        val f4 = color and 0xFF
        return Color(f2, f3, f4, f)
    }


    fun drawRoundedRect(x: Int, y: Int, width: Int, height: Int, cornerRadius: Int, color: Color) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.rgb)
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.rgb)
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.rgb)
        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color)
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color)
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color)
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color)
    }

    fun drawArc(x: Int, y: Int, radius: Int, startAngle: Int, endAngle: Int, color: Color) {
        GL11.glPushMatrix()
        GL11.glEnable(3042)
        GL11.glDisable(3553)
        GL11.glBlendFunc(770, 771)
        GL11.glColor4f(
            color.red.toFloat() / 255,
            color.green.toFloat() / 255,
            color.blue.toFloat() / 255,
            color.alpha.toFloat() / 255
        )
        val tessellator = Tessellator.getInstance()
        val worldRenderer = tessellator.buffer
        worldRenderer.begin(6, DefaultVertexFormats.POSITION)
        worldRenderer.pos(x.toDouble(), y.toDouble(), 0.0).endVertex()
        for (i in (startAngle / 360.0 * 100).toInt()..(endAngle / 360.0 * 100).toInt()) {
            val angle = Math.PI * 2 * i / 100 + Math.toRadians(180.0)
            worldRenderer.pos(x + sin(angle) * radius, y + cos(angle) * radius, 0.0).endVertex()
        }
        Tessellator.getInstance().draw()
        GL11.glEnable(3553)
        GL11.glDisable(3042)
        GL11.glPopMatrix()
    }

    fun drawRoundedOutline(x: Int, y: Int, x2: Int, y2: Int, radius: Float, width: Float, color: Int) {
        val f1 = (color shr 24 and 0xFF) / 255.0f
        val f2 = (color shr 16 and 0xFF) / 255.0f
        val f3 = (color shr 8 and 0xFF) / 255.0f
        val f4 = (color and 0xFF) / 255.0f
        GL11.glColor4f(f2, f3, f4, f1)
        drawRoundedOutline(x.toFloat(), y.toFloat(), x2.toFloat(), y2.toFloat(), radius, width)
    }

    fun drawRoundedOutline(x: Float, y: Float, x2: Float, y2: Float, radius: Float, width: Float) {
        val i = 18
        val j = 90 / i
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableCull()
        GlStateManager.enableColorMaterial()
        GlStateManager.blendFunc(770, 771)
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        if (width != 1.0f) GL11.glLineWidth(width)
        GL11.glBegin(3)
        GL11.glVertex2f(x + radius, y)
        GL11.glVertex2f(x2 - radius, y)
        GL11.glEnd()
        GL11.glBegin(3)
        GL11.glVertex2f(x2, y + radius)
        GL11.glVertex2f(x2, y2 - radius)
        GL11.glEnd()
        GL11.glBegin(3)
        GL11.glVertex2f(x2 - radius, y2 - 0.1f)
        GL11.glVertex2f(x + radius, y2 - 0.1f)
        GL11.glEnd()
        GL11.glBegin(3)
        GL11.glVertex2f(x + 0.1f, y2 - radius)
        GL11.glVertex2f(x + 0.1f, y + radius)
        GL11.glEnd()
        var f1 = x2 - radius
        var f2 = y + radius
        GL11.glBegin(3)
        var k: Int
        k = 0
        while (k <= i) {
            val m = 90 - k * j
            GL11.glVertex2f(
                (f1 + radius * ClientMathUtils.getRightAngle(m)).toFloat(),
                (f2 - radius * ClientMathUtils.getAngle(m)).toFloat()
            )
            k++
        }
        GL11.glEnd()
        f1 = x2 - radius
        f2 = y2 - radius
        GL11.glBegin(3)
        k = 0
        while (k <= i) {
            val m = k * j + 270
            GL11.glVertex2f(
                (f1 + radius * ClientMathUtils.getRightAngle(m)).toFloat(),
                (f2 - radius * ClientMathUtils.getAngle(m)).toFloat()
            )
            k++
        }
        GL11.glEnd()
        GL11.glBegin(3)
        f1 = x + radius
        f2 = y2 - radius
        k = 0
        while (k <= i) {
            val m = k * j + 90
            GL11.glVertex2f(
                (f1 + radius * ClientMathUtils.getRightAngle(m)).toFloat(),
                (f2 + radius * ClientMathUtils.getAngle(m)).toFloat()
            )
            k++
        }
        GL11.glEnd()
        GL11.glBegin(3)
        f1 = x + radius
        f2 = y + radius
        k = 0
        while (k <= i) {
            val m = 270 - k * j
            GL11.glVertex2f(
                (f1 + radius * ClientMathUtils.getRightAngle(m)).toFloat(),
                (f2 + radius * ClientMathUtils.getAngle(m)).toFloat()
            )
            k++
        }
        GL11.glEnd()
        if (width != 1.0f) GL11.glLineWidth(1.0f)
        GlStateManager.enableCull()
        GlStateManager.disableBlend()
        GlStateManager.disableColorMaterial()
        GlStateManager.enableTexture2D()
    }

    fun drawRect(left: Double, top: Double, right: Double, bottom: Double, color: Int) {
        var left = left
        var top = top
        var right = right
        var bottom = bottom
        if (left > right) {
            val cacheRight = right
            right = left
            left = cacheRight
        }
        if (top > bottom) {
            val cacheBottom = bottom
            bottom = top
            top = cacheBottom
        }
        val f3 = (color shr 24 and 255).toFloat() / 255.0f
        val f = (color shr 16 and 255).toFloat() / 255.0f
        val f1 = (color shr 8 and 255).toFloat() / 255.0f
        val f2 = (color and 255).toFloat() / 255.0f
        val tessellator = Tessellator.getInstance()
        val worldrenderer: BufferBuilder? = tessellator.buffer
        GlStateManager.enableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0)
        GlStateManager.color(f, f1, f2, f3)
        worldrenderer!!.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION)
        worldrenderer.pos(left, bottom, 0.0).endVertex()
        worldrenderer.pos(right, bottom, 0.0).endVertex()
        worldrenderer.pos(right, top, 0.0).endVertex()
        worldrenderer.pos(left, top, 0.0).endVertex()
        tessellator.draw()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawBorderedRect(
        x: Float, y: Float, x2: Float, y2: Float, width: Float,
        color1: Int, color2: Int
    ) {
        drawRect(x.toDouble(), y.toDouble(), x2.toDouble(), y2.toDouble(), color2)
        drawBorder(x, y, x2, y2, width, color1)
    }

    fun drawBorder(x: Float, y: Float, x2: Float, y2: Float, width: Float, color1: Int) {
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)
        glColor(color1)
        GL11.glLineWidth(width)
        GL11.glBegin(GL11.GL_LINE_LOOP)
        GL11.glVertex2d(x2.toDouble(), y.toDouble())
        GL11.glVertex2d(x.toDouble(), y.toDouble())
        GL11.glVertex2d(x.toDouble(), y2.toDouble())
        GL11.glVertex2d(x2.toDouble(), y2.toDouble())
        GL11.glEnd()
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
    }

}