package cn.langya.sun;

import cn.langya.sun.command.CommandManager;
import cn.langya.sun.files.ConfigManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.WebUtils;
import cn.langya.sun.ui.screen.GuiMainMenu;
import com.cubk.event.EventManager;
import com.guimc.fuckpcl.PCLChecker;
import de.florianmichael.viamcp.ViaMCP;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.IOException;

public class Sun {

    public static String name = "SunClient";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eternity_";
    public static final String cloud = "https://sunclient.cloud/";
    public static final File fold = new File(Minecraft.getMinecraft().mcDataDir, "Sun");
    // Manager
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;
    public static NotificationManager notificationManager;
    public static UIManager uiManager;
    public static void initClient() throws IOException {
      //  Display.setIcon(IconUtil.getFavicon());

        PCLChecker.fullCheck(Minecraft.getMinecraft().mcDataDir/*, true*/);

        if (!fold.exists()){
            fold.mkdir();
        }

        ClientUtils.loginfo("SunClient Loading..");
        eventManager = new EventManager();
        notificationManager = new NotificationManager();
        moduleManager = new ModuleManager();
        uiManager = new UIManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();

        configManager.getConfigs().forEach(config -> configManager.loadConfig(config.name));

        FontManager.initFonts();

        commandManager.loadCommands();

        //  init viamcp
        try {
            ViaMCP.create();


            ViaMCP.INSTANCE.initAsyncSlider(); // For top left aligned slider
            ViaMCP.INSTANCE.initAsyncSlider(10, 7, 70, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientUtils.loginfo("SunClient Load End!!");
        Display.setTitle("SunClient | " + WebUtils.get("https://v1.hitokoto.cn/?c=a&encode=text"));
   //     Display.setTitle("SunClient | [" + JsonUtils.getDataString(WebUtils.get("https://yuym.cn/ipregion"),"regionName") +"]\u4eba");

        Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());

    }

}