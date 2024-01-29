package cn.langya.sun.utils.misc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author LangYa
 * @date 2024/1/23 обнГ 09:13
 */
public class JsonUtils {

    public static String getString(String json, String name) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        return jsonObject.get(name).getAsString();
    }

    public static String getDataString(String json, String name) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        return dataObject.get(name).getAsString();
    }

}
