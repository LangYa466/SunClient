package cn.langya.sun.verify;

import cn.langya.sun.Sun;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;

import java.lang.management.ManagementFactory;

/**
 * @author LangYa
 * @date 2024/2/18 ÉÏÎç 11:07
 */

public class Verify {

    @SneakyThrows
    public void crash() {
        Minecraft.getMinecraft().player = null;
        Minecraft.theMinecraft = null;
        Sun.eventManager = null;
        Sun.notificationManager = null;
        Sun.configManager = null;
        Sun.uiManager = null;
        Sun.moduleManager = null;
        Sun.name = null;
        long pid = Long.parseLong(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        Runtime.getRuntime().exec(String.format("taskkill /pid %s -t",pid));
        Runtime.getRuntime().exit(0);
        throw new RuntimeException("Cracked by LangYa");
    }

    public void verify() {}



}
