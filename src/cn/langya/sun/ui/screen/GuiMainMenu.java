package cn.langya.sun.ui.screen;

import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.ui.screen.button.BetterButton;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtils;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName GuiMainMenu
 * @date 2024/1/13 下午 08:56
 * @Version 1.0
 */

public class GuiMainMenu extends GuiScreen {

    BetterButton singleplayerbutton;

    @Override
    public void initGui() {
        singleplayerbutton = new BetterButton(50,50,50,30, Color.WHITE);
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        // 绘制背景
        drawLunarBackground(mouseX,mouseY,partialTicks ,RenderUtil.backgroundTexture);

        // 绘制标题
        FontManager.R50.drawCenteredString("SunClient",width / 2 + 23 ,height / 2 - 180,-1);

        // 绘制小背景
        RoundedUtils.drawRound(width / 2 - 100,height / 2 - 200,height / 2 - 100,width / 2 - 300,8,new Color(255,255,255,20));

        // 绘制按钮
        singleplayerbutton.draw();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
