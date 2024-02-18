package cn.langya.sun.verify;

import cn.langya.sun.Sun;
import net.minecraft.client.Minecraft;

/**
 * @author LangYa
 * @date 2024/2/18 ионГ 11:07
 */

public class Verify {

    public void crash() {
        Minecraft.theMinecraft = null;
        Sun.eventManager = null;
        Sun.notificationManager = null;
        Sun.configManager = null;
        Sun.uiManager = null;
        Sun.moduleManager = null;
        Sun.name = null;
        Runtime.getRuntime().exit(0);
        throw new RuntimeException("Cracked by LangYa");
    }

    public void verify() {}



}
