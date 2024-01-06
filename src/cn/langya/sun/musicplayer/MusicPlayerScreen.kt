package cn.langya.sun.musicplayer

import cn.langya.sun.ui.FontManager
import cn.langya.sun.utils.render.RenderUtil
import javazoom.jl.player.Player
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import java.awt.Color
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL


/**
 * @author LangYa
 * @ClassName GuiScreen
 * @date 2023/12/30 19:38
 * @Version 1.0
 */

class MusicPlayerScreen: GuiScreen() {
    val musicPlayer = MusicPlayer()
    var text: GuiTextField? = null

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        text = GuiTextField(0,mc.fontRendererObj,RenderUtil.getWidth() / 2 - 30,RenderUtil.getHeight() / 2 - 20,100,20)

        // 绘制背景
        RenderUtil.drawRoundedRect(0,0,RenderUtil.getWidth(),RenderUtil.getHeight(),0, Color(0,0,0,40))
        RenderUtil.drawRoundedRect(0,0,RenderUtil.getWidth() / 2,RenderUtil.getHeight() / 2,4, Color.WHITE)

        FontManager.drawString("音乐播放器",RenderUtil.getWidth() / 2 - 100,RenderUtil.getHeight() / 2,-1)
        text!!.drawTextBox()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        text!!.mouseClicked(mouseX, mouseY, button)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        text!!.textboxKeyTyped(typedChar, keyCode)

        if (keyCode == 28 && text!!.text.isNotEmpty()) { // 回车键
            val musicid = musicPlayer.search(text!!.text)
            FontManager.drawString("${musicPlayer.getSongName(musicid)}-${musicPlayer.getSingerName(musicid)}",RenderUtil.getWidth() / 2 - 40,RenderUtil.getHeight() / 2 - 20,-1)
            musicPlayer.playUrlMusic(musicPlayer.getSongFile(musicid))
        }

    }

}