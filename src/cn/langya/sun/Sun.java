package cn.langya.sun;

import cn.langya.sun.command.CommandManager;
import cn.langya.sun.files.ConfigManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.WebUtils;
import cn.langya.sun.utils.render.ShaderUtil;
import cn.langya.sun.verify.HWIDVerify;
import com.cubk.event.EventManager;
import de.florianmichael.viamcp.ViaMCP;
import dev.jnic.annotations.Jnic;
import nellyobfuscator.NellyClassObfuscator;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.IOException;

@Jnic
@NellyClassObfuscator
public class Sun {

    public static final String name = "SunClient";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eternity_";
    public static final String cloud = "http://www.sunclient.cloud/";
    public static final File fold = new File(Minecraft.getMinecraft().mcDataDir, "Sun");
    // Manager
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;
    public static ShaderUtil shaderUtil;

    public void initClient() throws IOException {
        if (!fold.exists()){
            fold.mkdir();
        }
        ClientUtils.loginfo("SunClient Verifying..");
        HWIDVerify.verify();
        ClientUtils.loginfo("SunClient Verify Okay!!");

        ClientUtils.loginfo("SunClient Loading..");
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        shaderUtil.init();

        configManager.getConfigs().forEach(config -> configManager.loadConfig(config.name));
        //  init viamcp
        try {
            ViaMCP.create();

            // In case you want a version slider like in the Minecraft options, you can use this code here, please choose one of those:

            ViaMCP.INSTANCE.initAsyncSlider(); // For top left aligned slider
            ViaMCP.INSTANCE.initAsyncSlider(10, 7, 70, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // configManager.loadAllConfigs();
        ClientUtils.loginfo("SunClient Load End!!");
        Display.setTitle("SunClient | " + WebUtils.get("https://v1.hitokoto.cn/?c=a&encode=text"));
    }
}