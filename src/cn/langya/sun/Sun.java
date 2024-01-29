package cn.langya.sun;

import cn.langya.sun.command.CommandManager;
import cn.langya.sun.files.ConfigManager;
import cn.langya.sun.modules.ModuleManager;
import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.JsonUtils;
import cn.langya.sun.utils.misc.WebUtils;
import cn.langya.sun.utils.render.ShaderUtil;
import cn.langya.sun.verify.HWIDVerify;
import com.cubk.event.EventManager;
import com.guimc.fuckpcl.PCLChecker;
import de.florianmichael.viamcp.ViaMCP;
import dev.jnic.annotations.Jnic;
import nellyobfuscator.NellyClassObfuscator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.apache.commons.compress.utils.IOUtils;
import org.lwjgl.opengl.Display;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Jnic
@NellyClassObfuscator
public class Sun {

    public static final String name = "SunClient";
    public static final String version = "1.0";
    public static final String author = "LangYa,PaiMon,Eternity_";
    public static final String cloud = "https://sunclient.cloud/";
    public static final File fold = new File(Minecraft.getMinecraft().mcDataDir, "Sun");
    // Manager
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;

    public void initClient() throws IOException {
        setWindowIcon();

        PCLChecker.fullCheck(Minecraft.getMinecraft().mcDataDir/*, true*/);

        if (!fold.exists()){
            fold.mkdir();
        }

        ClientUtils.loginfo("SunClient Verifying..");
     //   HWIDVerify.verify();
        ClientUtils.loginfo("SunClient Verify Okay!!");

        ClientUtils.loginfo("SunClient Loading..");
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();

        configManager.getConfigs().forEach(config -> configManager.loadConfig(config.name));

        FontManager.initFonts();

        //  init viamcp
        try {
            ViaMCP.create();

            // In case you want a version slider like in the Minecraft options, you can use this code here, please choose one of those:

            ViaMCP.INSTANCE.initAsyncSlider(); // For top left aligned slider
            ViaMCP.INSTANCE.initAsyncSlider(10, 7, 70, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientUtils.loginfo("SunClient Load End!!");
    //    Display.setTitle("SunClient | " + WebUtils.get("https://v1.hitokoto.cn/?c=a&encode=text"));
        Display.setTitle("SunClient | [" + JsonUtils.getDataString(WebUtils.get("https://yuym.cn/ipregion"),"regionName") +"]»À");
    }

    private void setWindowIcon() {
        final Util.EnumOS util = Util.getOSType();
        if (util != Util.EnumOS.OSX) {
            InputStream inputstream = null;
            InputStream inputstream2 = null;
            Minecraft mc = Minecraft.getMinecraft();
            try {
                inputstream = mc.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("sunclient/icons/icon_16x16.png"));
                inputstream2 = mc.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("sunclient/icons/icon_32x32.png"));
                if (inputstream != null && inputstream2 != null) {
                    Display.setIcon(new ByteBuffer[] { mc.readImageToBuffer(inputstream), mc.readImageToBuffer(inputstream2) });
                }
            }
            catch (IOException ioexception) {
                ClientUtils.logger.error("Couldn't set icon", ioexception);
            }
            finally {
                IOUtils.closeQuietly(inputstream);
                IOUtils.closeQuietly(inputstream2);
            }
        }
    }

}