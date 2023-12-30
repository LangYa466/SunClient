package cn.langya.sun.musicplayer

import cn.langya.sun.ui.FontManager
import cn.langya.sun.utils.render.RenderUtil
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import java.awt.Color


/**
 * @author LangYa
 * @ClassName GuiScreen
 * @date 2023/12/30 19:38
 * @Version 1.0
 */

class MusicPlayerScreen: GuiScreen() {

    val text = GuiTextField(0,mc.fontRendererObj,RenderUtil.getWidth() / 2 - 30,RenderUtil.getHeight() / 2 - 20,100,20)

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        // 绘制背景
        RenderUtil.drawRoundedRect(0,0,RenderUtil.getWidth(),RenderUtil.getHeight(),0, Color(0,0,0,150))
        RenderUtil.drawRoundedRect(0,0,RenderUtil.getWidth() / 2,RenderUtil.getHeight() / 2,0, Color.WHITE)

        FontManager.drawString("Music Player",RenderUtil.getWidth() / 2 - 100,RenderUtil.getHeight() / 2,-1)
        text.drawTextBox()
        text.text
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        text.mouseClicked(mouseX, mouseY, button)
    }

    override fun updateScreen() {
        text.updateCursorCounter()
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        text.textboxKeyTyped(typedChar, keyCode)
        if (keyCode == 28 && text.text.isNotEmpty()) { // 回车键
            val musicPlayer = MusicPlayer()
            val json = musicPlayer.search("text")
        }
    }

}