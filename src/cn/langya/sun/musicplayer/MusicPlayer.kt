package cn.langya.sun.musicplayer

import cn.langya.sun.utils.misc.JsonUtils
import cn.langya.sun.utils.misc.WebUtils
import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * @author LangYa
 * @ClassName MusicPlayer
 * @date 2023/12/30 18:41
 * @Version 1.0
 */

class MusicPlayer {

    // 搜索歌曲返回Json
    fun search(name: String): String? {
        val jsonObject: JsonObject = Gson().fromJson(WebUtils.get("https://music-api.heheda.top/search?keywords=${name}A&limit=1&type=1&offset=0"), JsonObject::class.java)
        val songsArray = jsonObject.getAsJsonObject("result").getAsJsonArray("songs")

        val songObject: JsonObject? = songsArray.asJsonObject

        return WebUtils.get("https://api.baka.fun/netease?id=${songObject?.get("id")}")
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