package cn.langya.sun.musicplayer

import cn.langya.sun.utils.misc.JsonUtils
import cn.langya.sun.utils.misc.WebUtils
import java.io.IOException
import java.net.URL
import javax.sound.sampled.*


/**
 * @author LangYa
 * @ClassName MusicPlayer
 * @date 2023/12/30 18:41
 * @Version 1.0
 */

class MusicPlayer {

    fun playUrlMusic(url: String?) {
        try {
            // 创建音频流对象
            val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(URL(url))
            // 获取音频格式
            val audioFormat = audioInputStream.format
            // 转换音频格式
            val info = DataLine.Info(SourceDataLine::class.java, audioFormat)
            // 打开音频设备
            val line = AudioSystem.getLine(info) as SourceDataLine

            // 开始音频流播放
            line.open(audioFormat)
            line.start()

            // 读取数据到缓冲区
            val bufferSize = audioFormat.sampleRate.toInt() * audioFormat.frameSize
            val buffer = ByteArray(bufferSize)
            var bytesRead = -1
            while (audioInputStream.read(buffer).also { bytesRead = it } != -1) {
                line.write(buffer, 0, bytesRead)
            }

            // 停止音频流播放
            line.stop()
            line.close()
            audioInputStream.close()
        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // 搜索歌曲返回Json
    fun search(id: String): String {
        var json = ""
        try {
            json = WebUtils.get("https://api.baka.fun/netease?id=$id")
        } catch (e: IOException) {
           e.printStackTrace()
        }

        return json
    }

    // 获取歌曲文件
    fun getSongFile(json: String): String {
        return JsonUtils.getString(search(json), "url")
    }

    // 获取歌曲图标
    fun getSongIcon(json: String,witdh: Int,height: Int): String {
        return JsonUtils.getString(search(json), "cover").replace("300y300", "${witdh}y$height")
    }


    // 获取歌曲名称
    fun getSongName(json: String): String {
        return JsonUtils.getString(search(json),"title")
    }

    //获取歌手名称
    fun getSingerName(json: String): String {
        return JsonUtils.getString(search(json),"artist")
    }

    //热歌榜随机歌曲
    fun getHotSong(): String {
        return JsonUtils.getDataString(WebUtils.get("https://dataiqs.com/api/netease/music/?type=random"),"song_url")
    }



}