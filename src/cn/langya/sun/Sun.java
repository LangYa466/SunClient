package cn.langya.sun;

import cn.langya.sun.command.CommandManager;
import cn.langya.sun.files.ConfigManager;
import cn.langya.sun.files.FileManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.WebUtils;
import com.cubk.event.EventManager;
import org.lwjgl.opengl.Display;

import java.io.IOException;

public class Sun {

    public static final String name = "SunClient";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eovo";

    // Manager
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static UIManager uiManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;

    public void initClient() throws IOException, InstantiationException, IllegalAccessException {
        ClientUtils.loginfo("SunClient Loading..");
        eventManager = new EventManager();
        uiManager = new UIManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        commandManager.init();

        // configManager.loadAllConfigs();
        ClientUtils.loginfo("SunClient Load End!!");
        Display.setTitle("SunClient | " + WebUtils.get("https://v1.hitokoto.cn/?c=a&encode=text"));
    }


}