package cn.langya.sun.files.configs

import cn.langya.sun.Sun
import cn.langya.sun.files.Config
import cn.langya.sun.files.FileManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import java.io.File
import java.io.FileWriter
import java.io.IOException


/**
 * @author LangYa
 * @ClassName HUDConfig
 * @date 2024/1/6 上午 11:16
 * @Version 1.0
 */

class ModuleConfig: Config() {

    override fun save() {
        val jsonArray = JsonArray()

        for (modules in Sun.moduleManager.modules) {
            val elementObject = JsonObject()

            elementObject.addProperty("Name", modules.name)
            elementObject.addProperty("State", modules.state)
            elementObject.addProperty("Array", modules.array)

            jsonArray.add(elementObject)
        }

        try {
            FileWriter(FileManager.clientPath + File.separator + "modules.json").use { fileWriter ->
                fileWriter.write(
                    jsonArray.toString()
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun load() {


        try {
            val jsonString = File(FileManager.clientPath + File.separator + "modules.json").readText()
            val jsonArray = try {
                Gson().fromJson(jsonString, JsonArray::class.java)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                return
            }

            val moduleMap = Sun.moduleManager.modules.associateBy { it.name }

            for (jsonElement in jsonArray) {
                val jsonObject = jsonElement.asJsonObject

                val name = jsonObject.get("Name")?.asString ?: continue
                val state = jsonObject.get("State")?.asBoolean ?: continue
                val array = jsonObject.get("Array")?.asBoolean ?: continue

                moduleMap[name]?.apply {
                    this.state = state
                    this.array = array
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}