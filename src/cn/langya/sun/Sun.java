package cn.langya.sun;

import cn.langya.sun.event.EventProtocol;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.utils.ClientUtils;

public class Sun {

    public static final String name = "太阳客户端";
    public static final String version = "1.0";
    public static final String author = "狼牙466";

    // Manager
    public static ModuleManager moduleManager;
    public static EventProtocol eventProtocol;

    public static void initClient() {
        ClientUtils.loginfo("太阳客户端加载中!!!");
        eventProtocol = new EventProtocol();
        moduleManager = new ModuleManager();
        moduleManager.init();
        ClientUtils.loginfo("太阳客户端加载完毕!!!");
    }
}
