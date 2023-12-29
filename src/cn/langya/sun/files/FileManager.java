package cn.langya.sun.files;

import net.minecraft.client.Minecraft;

/**
 * @author LangYa
 * @ClassName FileManager
 * @date 2023/12/28 20:19
 * @Version 1.0
 */

public class FileManager {
    public static String clientPath = Minecraft.getMinecraft().mcDataDir.toString() + "/" + "SunClient";

}
