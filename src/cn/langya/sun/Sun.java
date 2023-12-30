package cn.langya.sun;

import cn.enaium.cf4m.CF4M;
import cn.enaium.cf4m.container.EventContainer;
import cn.langya.sun.files.FileManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.utils.ClientUtils;

public class Sun {

    public static final String name = "太阳客户端";
    public static final String version = "1.0";
    public static final String author = "LangYa";

    // Manager
    public static ModuleManager moduleManager;
    public static UIManager uiManager;
    public static FileManager fileManager;
    public static EventContainer eventManager;

    public void initClient() {
        ClientUtils.loginfo("太阳客户端 加载中..");
        CF4M.run(this, fileManager.clientPath);
        eventManager = CF4M.EVENT;
        moduleManager = new ModuleManager();
        uiManager = new UIManager();
        uiManager.init();
        ClientUtils.loginfo("太阳客户端 加载完毕!!");
    }


}