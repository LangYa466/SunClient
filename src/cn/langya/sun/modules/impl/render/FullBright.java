package cn.langya.sun.modules.impl.render;

import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import net.minecraft.client.Minecraft;

/**
 * @author LangYa
 * @ClassName FullBright
 * @date 2024/1/13 上午 11:13
 * @Version 1.0
 */

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", Category.Render);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 300;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
        super.onDisable();
    }
}
