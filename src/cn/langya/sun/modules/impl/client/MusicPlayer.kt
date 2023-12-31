package cn.langya.sun.modules.impl.client

import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.musicplayer.MusicPlayer
import cn.langya.sun.musicplayer.MusicPlayerScreen
import cn.langya.sun.ui.FontManager
import cn.langya.sun.utils.render.RenderUtil
import javazoom.jl.player.Player
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.URL

/**
 * @author LangYa
 * @ClassName MusicPlayer
 * @date 2023/12/30 20:15
 * @Version 1.0
 */
class MusicPlayer : Module("音乐播放器", true, Category.Client) {
    override fun onEnable() {
        val musicPlayer = MusicPlayer()

        val music = musicPlayer.search("346089")
        val url = URL(musicPlayer.getSongFile(music))
        val `in`: InputStream = url.openStream()
        val bufIn = BufferedInputStream(`in`)
        val player = Player(bufIn)
        player.play()
    }
}
