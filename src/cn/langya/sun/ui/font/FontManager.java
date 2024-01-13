package cn.langya.sun.ui.font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;

import java.awt.*;

public abstract class FontManager {
    // SFUI
    public static FontDrawer S15;
    public static FontDrawer S20;
    public static FontDrawer S25;
    public static FontDrawer S30;
    // 原神字体
    public static FontDrawer C15;
    public static FontDrawer C20;
    public static FontDrawer C25;
    public static FontDrawer C30;
    //Round 太帅了我滴妈
    public static FontDrawer R15;
    public static FontDrawer R18;
    public static FontDrawer R20;
    public static FontDrawer R25;
    public static FontDrawer R30;



    public static void initFonts() {
        LogManager.getLogger().info("Loading FontManager..");

        S15 = getFont("sfui.ttf", 15, true);
        S20 = getFont("sfui.ttf", 20, true);
        S25 = getFont("sfui.ttf", 25, true);
        S30 = getFont("sfui.ttf", 30, true);

        C15 = getFont("genshin.ttf", 15, true);
        C20 = getFont("genshin.ttf", 20, true);
        C25 = getFont("genshin.ttf", 25, true);
        C30 = getFont("genshin.ttf", 30, true);

        R15 = getFont("round.ttf", 15, true);
        R18 = getFont("round.ttf", 18, true);
        R20 = getFont("round.ttf", 20, true);
        R25 = getFont("round.ttf", 25, true);
        R30 = getFont("round.ttf", 30, true);

    }

    public static FontDrawer getFont(String name, int size, boolean antiAliasing) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("sunclient/fonts/" + name)).getInputStream()).deriveFont(Font.PLAIN, (float) size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, size);
        }
        return new FontDrawer(font, antiAliasing);
    }
}
