package cn.langya.sun;

import cn.langya.sun.files.FileManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.UIManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.WebUtils;
import com.cubk.event.EventManager;
import org.lwjgl.opengl.Display;

import java.io.IOException;

public class Sun {

    public static final String name = "太阳客户端";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eovo";

    // Manager
    public static ModuleManager moduleManager;
    public static UIManager uiManager;
    public static EventManager eventManager;

    public void initClient() throws IOException, InstantiationException, IllegalAccessException {
        ClientUtils.loginfo("太阳客户端 加载中..");
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        uiManager = new UIManager();
        uiManager.init();
        ClientUtils.loginfo("太阳客户端 加载完毕!!");
        Display.setTitle("太阳客户端 | " + WebUtils.get("https://v1.hitokoto.cn/?c=a&encode=text"));
    }


}