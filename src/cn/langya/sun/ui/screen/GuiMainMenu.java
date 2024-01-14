package cn.langya.sun.ui.screen;

import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.ui.screen.button.BetterButton;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.utils.misc.TimeUtil;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtils;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;

import java.awt.*;
import java.io.IOException;

/**
 * @author LangYa
 * @ClassName GuiMainMenu
 * @date 2024/1/13 下午 08:56
 * @Version 1.0
 */

public class GuiMainMenu extends GuiScreen {

    BetterButton singlPlayerButton;
    BetterButton multiPlayerButton;
    final Color bgcolor = new Color(0,0,0,150);
    final int width = 1280;
    final int height = 685;

    @Override
    public void initGui() {
        int buttonY = height / 2 - 140;
        singlPlayerButton = new BetterButton(width / 2 - 55,height / 2 - 140,160,30,bgcolor);
        multiPlayerButton = new BetterButton(width / 2 - 55,height / 2 - 140 + 40,160,30,bgcolor);
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        // 绘制背景
        drawLunarBackground(mouseX,mouseY,partialTicks ,RenderUtil.backgroundTexture);

        // 绘制标题
        FontManager.T50.drawCenteredString("SunClient",width / 2 + 23 ,height / 2 - 180,-1);

        // 绘制小背景
        RoundedUtils.drawRound(width / 2 - 100,height / 2 - 200,height / 2 - 100,width / 2 - 300,8,new Color(255,255,255,20));

        // 绘制按钮
        singlPlayerButton.draw("SinglePlayer",FontManager.S30,mouseX,mouseY);
        multiPlayerButton.draw("Multiplayer",FontManager.S30,mouseX,mouseY);

        // 绘制时间文本
        FontManager.T50.drawCenteredString(TimeUtil.getTime(),1200,20,-1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (singlPlayerButton.hoverFade >0){
            mc.displayGuiScreen(new GuiWorldSelection(this));
        }

        if (multiPlayerButton.hoverFade >0){
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
