package cn.langya.sun.ui.screen;

import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName BestGuiMainMenu
 * @date 2024/1/13 下午 05:42
 * @Version 1.0
 */

public class BestGuiMainMenu extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawLunarBackground(mouseX,mouseY,partialTicks,RenderUtil.backgroundTexture);

        RoundedUtil.drawRound(0,0,130,1980,0, new Color(0,0,0,120));
        FontManager.T50.drawString("SunClient",6,20,-1);

        final int sbcolor = new Color(175,169,169).getRGB();

        // 我也不知道这个有啥用
        RoundedUtil.drawRound(185,150,130,200,5, new Color(0,0,0,120));
        FontManager.C30.drawString("SBSBSBS",215,175,sbcolor);
        FontManager.C30.drawString("SBSBSB2",215,195,sbcolor);
        FontManager.C30.drawString("SBSBSB3",215,215,sbcolor);
        FontManager.C30.drawString("SBSBSB4",215,235,sbcolor);
        FontManager.C30.drawString("SBSBSB5",215,255,sbcolor);
        FontManager.C30.drawString("SBSBSB6",215,275,sbcolor);
        FontManager.C30.drawString("SBSBSB7",215,295,sbcolor);
        FontManager.C30.drawString("SBSBSB8",215,315,sbcolor);

        // Servers List
        RoundedUtil.drawRound(370,80,800,400,5, new Color(0,0,0,120));

        RoundedUtil.drawRound(370,80,800,80,5, new Color(0,0,0,200));
        RenderUtil.drawImage(new ResourceLocation("sunclient/icons/img.png"),390,108,32,32);
        FontManager.T25.drawCenteredString("Fake Hypixel",500,109,sbcolor);

        FontManager.T50.drawCenteredString("05:20 PM",1200,20,-1);

        // button
        RoundedUtil.drawRound(0,130,130,40,0,new Color(14,102,173));
        RoundedUtil.drawRound(0,130,30,40,0,new Color(11,71,123));
        FontManager.T30.drawString("Test",43,140,-1);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
