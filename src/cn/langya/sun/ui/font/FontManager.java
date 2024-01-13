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
    // 宋体魔改
    public static FontDrawer C15;
    public static FontDrawer C20;
    public static FontDrawer C25;
    public static FontDrawer C30;



    public static void initFonts() {
        LogManager.getLogger().info("Loading FontManager..");

        S15 = getFont("sfui.ttf", 15, true);
        S20 = getFont("sfui.ttf", 20, true);
        S25 = getFont("sfui.ttf", 25, true);
        S30 = getFont("sfui.ttf", 30, true);

        C15 = getFont("haison.ttf", 15, true);
        C20 = getFont("haison.ttf", 20, true);
        C25 = getFont("haison.ttf", 25, true);
        C30 = getFont("haison.ttf", 30, true);

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
