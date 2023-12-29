package cn.langya.sun.ui.screen.mainmenu;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.FontManager;
import cn.langya.sun.ui.screen.mainmenu.buttons.ImageButton;
import cn.langya.sun.ui.screen.mainmenu.buttons.MainButton;
import cn.langya.sun.ui.screen.mainmenu.buttons.QuitButton;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class MainMenu extends GuiScreen {
	
	private ResourceLocation logo;
	
	private MainButton btnSingleplayer;
	private MainButton btnMultiplayer;
	private ImageButton btnMinecraftOptions;
	private ImageButton btnLanguage;
	
	private QuitButton btnQuit;
    private ResourceLocation backgroundTexture;

    @Override
    public void initGui() {
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", new DynamicTexture(256, 256));
        
        this.logo = new ResourceLocation("lunar/logo.png");
        
        this.btnSingleplayer = new MainButton("单人世界", this.width / 2 - 66, this.height / 2);
        this.btnMultiplayer = new MainButton("多人游戏", this.width / 2 - 66, this.height / 2 + 15);

        int yPos = this.height - 20;
        this.btnMinecraftOptions = new ImageButton("设置", new ResourceLocation("lunar/icons/cog.png"), this.width / 2, yPos);
        this.btnLanguage = new ImageButton("语言", new ResourceLocation("lunar/icons/globe.png"), this.width / 2 + 15, yPos);
        
        this.btnQuit = new QuitButton(this.width - 17, 7);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawLunarBackground(mouseX, mouseY, partialTicks,backgroundTexture);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(logo);
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 25, this.height / 2 - 68, 0, 0, 49, 49, 49, 49);

        //        FontUtil.TITLE.getFont().drawCenteredString("LUNAR CLIENT", this.width / 2 - 0.25F, this.height / 2 - 18, new Color(30, 30, 30, 70).getRGB());
        //        FontUtil.TITLE.getFont().drawCenteredString("LUNAR CLIENT", this.width / 2, this.height / 2 - 19, -1);

        FontManager.drawCenteredString("太阳客户端", this.width / 2 - 0.25F, this.height / 2 - 18, new Color(30, 30, 30, 70).getRGB());
        FontManager.drawCenteredString("太阳客户端", this.width / 2, this.height / 2 - 19, -1);
        
        this.btnSingleplayer.drawButton(mouseX, mouseY);
        this.btnMultiplayer.drawButton(mouseX, mouseY);

        this.btnMinecraftOptions.drawButton(mouseX, mouseY);
        this.btnLanguage.drawButton(mouseX, mouseY);
        
        this.btnQuit.drawButton(mouseX, mouseY);
        
        String s = "版权归 狼牙土豆工作室 所有！";
        FontManager.drawString("太阳客户端 1.12.2 ("+ Sun.version +")", 7, this.height - 11, new Color(255, 255, 255, 100).getRGB());
        FontManager.drawString(s, this.width - FontManager.getStringWidth(s) - 6, this.height - 11, new Color(255, 255, 255, 100).getRGB());
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {

        //boolean hovered = HoveringUtil.isHovering(x, y, width, height, mouseX, mouseY);
        if (this.btnSingleplayer.hoverFade >0){
            mc.displayGuiScreen(new GuiWorldSelection(this));
        }
        if (this.btnMultiplayer.hoverFade >0) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (this.btnQuit.hoverFade >0){
            mc.shutdown();
        }
        if (this.btnMinecraftOptions.hoverFade >0) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if (this.btnLanguage.hoverFade >0) {
            mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }


    }

}
