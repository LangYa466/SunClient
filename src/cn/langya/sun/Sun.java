package cn.langya.sun;

import cn.langya.sun.command.CommandManager;
import cn.langya.sun.files.ConfigManager;
import cn.langya.sun.files.FileManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UiManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.WebUtils;
import com.cubk.event.EventManager;
import de.florianmichael.viamcp.ViaMCP;
import org.lwjgl.opengl.Display;

import java.io.IOException;

public class Sun {

    public static final String name = "SunClient";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eovo";

    // Manager
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static UiManager uiManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;

    public void initClient() throws IOException, InstantiationException, IllegalAccessException {
        ClientUtils.loginfo("SunClient Loading..");
        eventManager = new EventManager();
        uiManager = new UiManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        commandManager.init();

        // init viamcp
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