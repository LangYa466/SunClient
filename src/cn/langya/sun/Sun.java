package cn.langya.sun;

import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.utils.ClientUtils;

public class Sun {

    public static final String name = "SunClinet";
    public static final String version = "1.0";
    public static final String author = "LangYa";

    // Manager
    public static ModuleManager moduleManager;
    public static UIManager uiManager;

    public static void initClient() {
        ClientUtils.loginfo("SunClient initing");
        moduleManager = new ModuleManager();
        moduleManager.init();
        uiManager = new UIManager(1, 0, 200, 300);
        ClientUtils.loginfo("SunClient okay");
    }
}
